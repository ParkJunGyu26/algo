#include <iostream>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

	int t;
	cin >> t;

	int sosu[1000001] = {0};
	sosu[1] = 1;

	for (int i = 2; i < 1000001; i++) {
		for (int j = i*2; j < 1000001; j += i) {
			if (sosu[j] == 0) sosu[j] = 1;
		}
	}

	for (int i = 0; i < t; i++) {
		int n;
		cin >> n;

		int cnt = 0;
		for (int j = 2; j <= (int)(n/2); j++) {
			if (sosu[j] == 0 && sosu[n-j] == 0) cnt++;
		}

		cout << cnt << "\n";
	}
}