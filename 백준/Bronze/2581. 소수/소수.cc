#include <iostream>

using namespace std;

int main() {
	int N, M;
	cin >> M >> N;

	int sosu[10001] = {0};
	sosu[1] = 1;

	for (int i = 2; i < 10001; i++) {
		for (int j = i*2; j < 10001; j += i) {
			if (sosu[j] == 0) sosu[j] = 1;
		}
	}

	int total = 0;
	int ans = -1;
	for (int i = M; i <= N; i++) {
		if (sosu[i] == 0) {
			total += i;
			if (ans == -1) ans = i;
		}
	}

	if (ans == -1) cout << ans;
	else cout << total << "\n" << ans;
}