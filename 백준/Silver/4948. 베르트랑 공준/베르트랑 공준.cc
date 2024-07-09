#include <iostream>

using namespace std;

const int MAX = 123456 * 2;

int main()
{
	int n;
	while (true) {
		cin >> n;
		if (n == 0) break;
		
		int sosu[MAX] = {0};
		sosu[0] = 1;
		sosu[1] = 1;
		
		int ans = 0;
		// 에라토스테네스의 체
		for (int i = 2; i <= 2*n; i++) {
			for (int j = i*2; j <= 2*n; j += i) {
				if (sosu[j] == 0) sosu[j] = 1;
			}
		}

		for (int i = n+1; i <= 2*n; i++) if (sosu[i] == 0) ans++;

		cout << ans << endl;
	}

	return 0;
}