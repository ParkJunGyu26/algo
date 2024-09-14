#include <iostream>

using namespace std;

int rec[1000][1000] = {0};

int main() {
	int n;
	cin >> n;

	for (int i = 0; i < n; i++) {
		int x, y;
		cin >> x >> y;

		for (int yy = y; yy < y+10; yy++) {
			for (int xx = x; xx < x+10; xx++) {
				rec[yy][xx]++;
			}
		}
	}

	int ans = 0;
	for (int i = 0; i < 1000; i++) {
		for (int j = 0; j < 1000; j++) {
			if (rec[i][j] >= 1) {
				ans++;
			}
		}
	}

	cout << ans << endl;
}