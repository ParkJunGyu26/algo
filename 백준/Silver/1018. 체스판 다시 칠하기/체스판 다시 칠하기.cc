#include <iostream>

using namespace std;

int min(int a, int b) {
	if (a >= b) return b;
	return a;
}

int main() {
	char graph[51][51];
	int n, m;
	cin >> n >> m;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> graph[i][j];
		}
	}

	char target1[51][51];
	char target2[51][51];

	for (int i = 0; i < 8; i++) {
		for (int j = 0; j < 8; j++) {
			if ((i+j) % 2 == 0) {
				target1[i][j] = 'W';
				target2[i][j] = 'B';
			}
			else {
				target1[i][j] = 'B';
				target2[i][j] = 'W';
			}
		}
	}
	
	int ans = 50*50;
	int first, second;
	
	for (int i = 0; i <= n-8; i++) {
		for (int j = 0; j <= m-8; j++) {
			int cnt;
			first = second = 0;
			for (int y = i; y < i+8; y++) {
				for (int x = j; x < j+8; x++) {
					if (graph[y][x] != target1[y-i][x-j]) first++;
					if (graph[y][x] != target2[y-i][x-j]) second++;
				}
			}
			cnt = min(first, second);
			ans = min(cnt, ans);
		}
	}

	cout << ans;
}