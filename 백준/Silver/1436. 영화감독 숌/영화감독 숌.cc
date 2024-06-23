#include <iostream>
#include <vector>
#include <string>

using namespace std;

int main()
{
	vector<int> ans = { 666, 1666, 2666, 3666, 4666, 5666 };
	int n;

	cin >> n;
	if (n < 7) cout << ans[n-1];
	else {
		int answer = ans[5];
		while (n-6 > 0)
		{
			answer++;
			int cnt = 0;
			for (auto s : to_string(answer))
			{
				if (s == '6') cnt++;
				else cnt = 0;

				if (cnt == 3)
				{
					n--;
					break;
				}
			}
		}
		cout << answer;
	}

	return 0;
}
