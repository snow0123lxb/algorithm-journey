package class064;

import java.util.PriorityQueue;

// 最小体力消耗路径
// 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights
// 其中 heights[row][col] 表示格子 (row, col) 的高度
// 一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) 
// （注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动
// 你想要找到耗费 体力 最小的一条路径
// 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的
// 请你返回从左上角走到右下角的最小 体力消耗值
// 测试链接 ：https://leetcode.cn/problems/path-with-minimum-effort/
public class Code02_PathWithMinimumEffort {

	// 0:上，1:右，2:下，3:左
	public static int[] move = new int[] { -1, 0, 1, 0, -1 };

	public int minimumEffortPath(int[][] heights) {
		int n = heights.length;
		int m = heights[0].length;
		int[][] distance = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				distance[i][j] = Integer.MAX_VALUE;
			}
		}
		distance[0][0] = 0;
		boolean[][] visited = new boolean[n][m];
		PriorityQueue<int[]> heap = new PriorityQueue<int[]>((a, b) -> a[2] - b[2]);
		heap.add(new int[] { 0, 0, 0 });
		while (!heap.isEmpty()) {
			int[] record = heap.poll();
			int x = record[0];
			int y = record[1];
			int c = record[2];
			if (visited[x][y]) {
				continue;
			}
			if (x == n - 1 && y == m - 1) {
				return c;
			}
			visited[x][y] = true;
			for (int i = 0; i < 4; i++) {
				int nx = x + move[i];
				int ny = y + move[i + 1];
				if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
					int nc = Math.max(c, Math.abs(heights[x][y] - heights[nx][ny]));
					if (nc < distance[nx][ny]) {
						heap.add(new int[] { nx, ny, nc });
					}
				}
			}
		}
		return -1;
	}

}
