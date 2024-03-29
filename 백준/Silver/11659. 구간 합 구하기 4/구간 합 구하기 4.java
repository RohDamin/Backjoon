/*
구간합 배열(Prefix Sum)
*/
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr, sumArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        sumArr = new int[N+1];
        sumArr[0] = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i<=N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sumArr[i] = sumArr[i-1] + arr[i];
        }

        for (int i = 0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(sumArr[b] - sumArr[a-1]);
        }
    }
}