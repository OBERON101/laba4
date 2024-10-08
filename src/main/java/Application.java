import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import fileReaders.FileReaderHolder;
import fileReaders.FileTypeEnum;
import models.ReactorType;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Application {
    private final JFrame applicationFrame = new JFrame("Application");
    private JPanel applicationPanel;
    private JTree reactorTypesTree;
    private JButton fileChooserButton;
    private JButton openReactorsWindowButton;
    private final Map<FileTypeEnum, Map<String, ReactorType>> fileHistory = new HashMap<>();
    private FileTypeEnum currentFileType = null;
    private ReactorTree reactorTree;

    private final FileReaderHolder fileReaderHolder = new FileReaderHolder();


    public static void main(String[] args) {
        new Application();
    }

    public Application() {
        initApplicationFrame();
        initFileChooserButton();
        initOpenReactorsWindowButton();
    }

    private void initApplicationFrame() {
        applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        applicationFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        applicationFrame.setVisible(true);
        applicationFrame.getContentPane().add(BorderLayout.CENTER, applicationPanel);
    }

    private void initFileChooserButton() {
        fileChooserButton.addActionListener(actionEvent -> {
            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setCurrentDirectory(new File("./src/main/java/reactorTypes"));

            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "ReactorType files", "json", "xml", "yaml"
            );
            fileChooser.setFileFilter(filter);

            if (fileChooser.showOpenDialog(applicationPanel) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                if (!(file.getName().toLowerCase().endsWith(".yaml")) &&
                        !(file.getName().toLowerCase().endsWith(".xml")) &&
                        !(file.getName().toLowerCase().endsWith(".json"))) {
                    JOptionPane.showMessageDialog(
                            null, "Incorrect file format", "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                buildTree(file);
                openReactorsWindowButton.setEnabled(true);
            }
        });
    }

    private void initOpenReactorsWindowButton() {
        openReactorsWindowButton.addActionListener(actionEvent -> {
            if (reactorTree == null || !reactorTree.getState()) {
                reactorTree = new ReactorTree(fileHistory, currentFileType);
            }
        });
    }

    private void buildTree(File file) {
        currentFileType = FileTypeEnum.parseFileType("." + file.getName().split("\\.")[1]); // беру первый элемент после точки
        Map<String, ReactorType> map = fileReaderHolder.readReactorTypesFromFile(file.getAbsolutePath(),
                FileTypeEnum.parseFileType("." + file.getName().split("\\.")[1]));
        fileHistory.put(currentFileType, map);
        DefaultTreeModel treeModel = (DefaultTreeModel) reactorTypesTree.getModel();
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("reactorTypes");
        for (Map.Entry<String, ReactorType> entry : map.entrySet()) {
            DefaultMutableTreeNode reactorType = new DefaultMutableTreeNode(entry.getKey());
            rootNode.add(reactorType);
            for (DefaultMutableTreeNode node : entry.getValue().getTreeNodes()) {
                reactorType.add(node);
            }
        }
        treeModel.setRoot(rootNode);
        reactorTypesTree.setEnabled(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        applicationPanel = new JPanel();
        applicationPanel.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        reactorTypesTree = new JTree();
        applicationPanel.add(reactorTypesTree, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        fileChooserButton = new JButton();
        fileChooserButton.setText("Choose reactor types file");
        applicationPanel.add(fileChooserButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        openReactorsWindowButton = new JButton();
        openReactorsWindowButton.setEnabled(false);
        openReactorsWindowButton.setText("Open reactors window");
        applicationPanel.add(openReactorsWindowButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return applicationPanel;
    }

}
