//Matias Herrera y Renato Parra
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class main {

    public static void main(String[] args) {

        int[] sizes = {32, 64, 128, 256, 512, 1024};
        int numInstances = 100;

        for (int n : sizes) {

            int t = (int) (Math.log(n) / Math.log(2));

            Out out = new Out("resultados_n" + n + ".csv");
            out.println("cuentaPares,cuentaPares2,cuentaPares3");

            StdRandom.setSeed(1000 + t);

            for (int i = 0; i < numInstances; i++) {

                int maxK = (int) Math.pow(2, t - 2);
                int k = StdRandom.uniformInt(1, maxK + 1);
                int maxVal = (int) Math.pow(2, t + 2);
                Set<Integer> uniqueValues = new HashSet<>();

                while (uniqueValues.size() < n) {
                    uniqueValues.add(StdRandom.uniformInt(0, maxVal + 1));
                }

                List<Integer> baseList = new ArrayList<>(uniqueValues);

                List<Integer> list1 = new ArrayList<>(baseList);
                List<Integer> list2 = new ArrayList<>(baseList);
                List<Integer> list3 = new ArrayList<>(baseList);

                StopwatchCPU timer1 = new StopwatchCPU();
                Result.cuentaPares(n, list1, k);
                double time1 = timer1.elapsedTime() * 1000.0;

                StopwatchCPU timer2 = new StopwatchCPU();
                Result.cuentaPares2(n, list2, k);
                double time2 = timer2.elapsedTime() * 1000.0;

                StopwatchCPU timer3 = new StopwatchCPU();
                Result.cuentaPares3(n, list3, k);
                double time3 = timer3.elapsedTime() * 1000.0;

                out.printf("%.6f,%.6f,%.6f\n", time1, time2, time3);
            }
            out.close();
        }
    }
}