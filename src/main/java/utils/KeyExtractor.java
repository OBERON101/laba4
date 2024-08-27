package utils;

import models.Reactor;

@FunctionalInterface
public interface KeyExtractor {
    String extractKeyFromReactor(Reactor reactor);
}
