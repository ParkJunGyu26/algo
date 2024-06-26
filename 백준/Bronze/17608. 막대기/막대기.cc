#include <iostream>
#include <vector>

using namespace std;

int main()
{
	int n, tmp;
	cin >> n;

	vector<int> stick;
	for (int i = 0; i < n; i++) {
		cin >> tmp;
		stick.push_back(tmp);
	}

	int height = 0;
	int ans = 0;
	for (int i = n; i >= 0; i--) {
		if (stick[i] > height) {
			ans++;
			height = stick[i];
		}
	}

	cout << ans << endl;
	return 0;
}