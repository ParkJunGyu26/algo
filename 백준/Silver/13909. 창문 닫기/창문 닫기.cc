#include <iostream>
#include <vector>

#define MAX 2100000000

using namespace std;

// 제곱수 문제
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n;
	cin >> n;

	int num = 1;
	while (num * num < n) {
		num++;
	}
	if (n == 1) cout << 1;
	else	cout << num-1;
}