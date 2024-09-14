#include <iostream>

using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int count[10001] = {0};
	int n;
	cin >> n;

	for (int i = 0; i < n; i++) {
		int number;
		cin >> number;
		count[number]++;
	}

	for (int i = 0; i < 10001; i++) {
		if (count[i] != 0) {
			for (int j = 0; j < count[i]; j++) {
				printf("%d\n", i);
			}
		}
	}
}