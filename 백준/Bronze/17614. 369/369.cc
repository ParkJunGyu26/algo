#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

int main()
{
	int n;
	cin >> n;

	int ans = 0;
	
	for (int i = 1; i <= n; i++) {
		int cnt = 0;
		string num = to_string(i);

		for (int j = 0; j < num.size(); j++) {
			if (num[j] == '3' || num[j] == '6' || num[j] == '9') ans++;
		}
	}

	cout << ans;

	return 0;
}