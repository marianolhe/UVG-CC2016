package uvg;

import java.util.List;
/**
         * Clase auxiliar para almacenar los resultados de la interpretación
         */
        public class LispResult {
            public  List<Token> tokens = null;
            public Expression expression = null;
            public String javaCode = null;
            public Object result = null;
        }