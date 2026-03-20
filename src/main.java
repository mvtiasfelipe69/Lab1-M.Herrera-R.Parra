import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class main {

    public static void main(String[] args) {
        // Los tamaños a evaluar: 2^5, 2^6, 2^7, 2^8, 2^9, 2^10 [cite: 78]
        int[] sizes = {32, 64, 128, 256, 512, 1024};
        int numInstances = 100; // 100 instancias distintas por tamaño [cite: 82]

        for (int n : sizes) {
            // Calcular 't' donde n = 2^t
            int t = (int) (Math.log(n) / Math.log(2));

            // Archivo CSV para este tamaño específico [cite: 94]
            Out out = new Out("resultados_n" + n + ".csv");
            out.println("cuentaPares,cuentaPares2,cuentaPares3");

            // Semilla fija por tamaño para reproducibilidad [cite: 88]
            StdRandom.setSeed(1000 + t);

            System.out.println("Generando y midiendo para n = " + n + "...");

            for (int i = 0; i < numInstances; i++) {
                // Generar k en el rango [1, 2^(t-2)] [cite: 86-87]
                int maxK = (int) Math.pow(2, t - 2);
                // StdRandom.uniformInt(a, b) genera valores en [a, b), así que sumamos 1
                int k = StdRandom.uniformInt(1, maxK + 1);

                // Generar n valores distintos en el rango [0, 2^(t+2)] [cite: 84-85]
                int maxVal = (int) Math.pow(2, t + 2);
                Set<Integer> uniqueValues = new HashSet<>();

                while (uniqueValues.size() < n) {
                    uniqueValues.add(StdRandom.uniformInt(0, maxVal + 1));
                }

                // Convertir el Set a una Lista
                List<Integer> baseList = new ArrayList<>(uniqueValues);

                // Crear copias exactas para cada método, ya que algunos métodos
                // modifican (ordenan) la lista internamente.
                List<Integer> list1 = new ArrayList<>(baseList);
                List<Integer> list2 = new ArrayList<>(baseList);
                List<Integer> list3 = new ArrayList<>(baseList);

                // 1. Medir cuentaPares
                StopwatchCPU timer1 = new StopwatchCPU();
                Result.cuentaPares(n, list1, k);
                // Multiplicamos por 1000 para obtener milisegundos [cite: 93]
                double time1 = timer1.elapsedTime() * 1000.0;

                // 2. Medir cuentaPares2
                StopwatchCPU timer2 = new StopwatchCPU();
                Result.cuentaPares2(n, list2, k);
                double time2 = timer2.elapsedTime() * 1000.0;

                // 3. Medir cuentaPares3
                StopwatchCPU timer3 = new StopwatchCPU();
                Result.cuentaPares3(n, list3, k);
                double time3 = timer3.elapsedTime() * 1000.0;

                // Escribir los 3 tiempos en el CSV
                out.printf("%.6f,%.6f,%.6f\n", time1, time2, time3);
            }
            out.close();
        }
        System.out.println("¡Análisis empírico completado! Revisa los archivos CSV generados.");
    }
}