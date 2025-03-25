package uvg;

import java.util.List;

@FunctionalInterface
public interface Operation {
    Object apply(List<Object> args, Environment env);
}
