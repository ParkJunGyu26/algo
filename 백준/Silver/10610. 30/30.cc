#include <iostream>
#include <string>
#include <algorithm>
#include <vector>

using namespace std;

string n;

bool cmp(int a, int b) {
	return a > b;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	if (n.find('0') == n.npos) cout << -1;
	else {
		int sum = 0;
		for (auto a : n) sum += (a-'0');

		if (sum % 3 != 0) cout << -1;
		else {
			sort(n.begin(), n.end(), cmp);
			cout << n;
		}
	}
}