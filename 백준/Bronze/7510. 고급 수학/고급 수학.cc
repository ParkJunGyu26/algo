#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	vector<int> vec(3);
	for (int _ = 0; _ < n; _++) {
		for (int i = 0; i < 3; i++) cin >> vec[i];
		sort(vec.begin(), vec.end());

		cout << "Scenario #" << _+1 << ":\n";
		if (vec[2]*vec[2] == vec[0]*vec[0]+vec[1]*vec[1]) cout << "yes\n";
		else cout << "no\n";
		if (_ != n-1) cout << "\n";
	}
}