#include <iostream>

using namespace std;

int n;
int answer[3];

int main() {
	ios_base::sync_with_stdio(false);cout.tie(NULL);cin.tie(NULL);

	cin >> n;

	for (int a = 1; a <= n; a++) {
		for (int b = a; b <= (n / a); b++) {
			for (int c = b; c <= ((n / a) / b); c++) {
				if (a * b * c == n) {
					answer[0] = a;
					answer[1] = b;
					answer[2] = c;
				}
			}
		}
	}

	cout << answer[0] << " " << answer[1] << " " << answer[2];
}