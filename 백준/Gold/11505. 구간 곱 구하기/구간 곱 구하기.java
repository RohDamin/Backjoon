/*
 # 세그먼트 트리(Segment Tree)

 * 구간들을 보존하고 있는 트리
 * 리프 노드는 길이가 1인 각각의 구간을 갖고,
 * 부모 노드는 자식 노드들의 구간의 합을 가지고 있음
 * 모든 구간은 연속적이어야 함
 * disjoint한 집합들을 표현하는 자료구조
 * 보통 완전 이진 트리 형태를 사용해 전체적으로 동일한 재귀적 구조가 반복되도록 표현
 * 포화 이진 트리 형태로 사용하면 값이 N개일 때, 트리의 높이가 O(log N)으로 균형잡혀 있게 됨

 */
import java.io.*;
import java.util.*;

public class Main {
    static long[] tree, arr;
    static int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 수의 개수
        int M = Integer.parseInt(st.nextToken()); // update 횟수
        int K = Integer.parseInt(st.nextToken()); // mul 횟수

        arr = new long[N+1];
        tree = new long[N*4];

        for (int i = 1; i<=N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        init(1, N, 1);

        for (int i = 0; i<M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a==1) {
                arr[b] = c;
                update(1, N, 1, b, c);
            } else if (a==2) {
                sb.append(mul(1, N, 1, b, (int) c)).append("\n");
            }
        }

        System.out.println(sb);
        br.close();
    }

    // 구간 힙 트리 생성
    static long init (int start, int end, int node) {
        if (start == end) return tree[node] = arr[start];

        int mid = (start + end) / 2;

        // 재귀적으로 두 부분으로 나눈 뒤, 그 곱을 자기 자신으로 저장
        return tree[node] = (init(start, mid, node*2) * init(mid+1, end, node*2+1)) % MOD;
    }

    // 구간 곱을 구하는 함수
    static long mul (int start, int end, int node, int left, int right) {
        // 범위 밖에 있는 경우
        if (left > end || right < start) return 1;

        // 범위 안에 있는 경우
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        return (mul(start, mid, node*2, left, right) * mul(mid+1, end, node*2+1, left, right))%MOD;
    }

    // 특정 원소의 값을 수정하는 함수
    static long update (int start, int end, int node, int index, long dif) {
        // 범위 밖에 있는 경우
        if (index < start || index > end) return tree[node];

        // 범위 안에 있는 경우
        // 리프 노드 업데이트
        if (start == end) return tree[node] = dif;
        int mid = (start + end) / 2;
        return tree[node] = (update(start, mid, node*2, index, dif) * update(mid+1, end, node*2+1, index, dif))%MOD;
    }
}
