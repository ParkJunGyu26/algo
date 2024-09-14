#include <iostream>
#include <string>

using namespace std;

int main() {
	string A[5];

	for (int i = 0; i < 5; i++) {
		cin >> A[i];
	}

	string ans = "";
	for (int i = 0; i < 15; i++) {
		for (int j = 0; j < 5; j++) {
			if (A[j].size()-1 >= i) {
				ans += A[j][i];
			}
		}
	}

	cout << ans;
}