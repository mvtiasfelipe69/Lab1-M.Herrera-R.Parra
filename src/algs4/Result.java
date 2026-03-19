
import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

public class Result {

  public static int cuentaPares(int pN, List<Integer> pValores, int pK) {
    int izq = 0;
    int der = 0;
    int res = 0;

    pValores.sort(null);
    while (der < pValores.size()) {
      if (pValores.get(izq) + pK > pValores.get(der)) {
        der += 1;
      } else if (pValores.get(izq) + pK == pValores.get(der)) {
        res += 1;
        izq += 1;
        der += 1;
      } else if (pValores.get(izq) + pK < pValores.get(der)) {
        izq += 1;
      }
    }
    return res;
  }

  public static int cuentaPares2(int pN, List<Integer> pValores, int pK) {
    int res = 0;
    pValores.sort(null);
    for (int i = 0; i < pValores.size(); i++) {
      if (Collections.binarySearch(pValores, pValores.get(i) + pK) >= 0) {
        res += 1;
      }
    }
    return res;
  }

  public static int cuentaPares3(int pN, List<Integer> pValores, int pK) {
    int res = 0;
    for (int i = 0; i < pValores.size(); i++) {
      for (int j = i + 1; j < pValores.size(); j++) {
        if (Math.abs(pValores.get(i) - pValores.get(j)) == pK) {
          res += 1;
        }
      }
    }
    return res;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

    String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    int n = Integer.parseInt(firstMultipleInput[0]);

    int pK = Integer.parseInt(firstMultipleInput[1]);

    List<Integer> valores = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
        .map(Integer::parseInt)
        .collect(toList());

    int res = Result.cuentaPares(n, valores, pK);

    bufferedWriter.write(String.valueOf(res));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
