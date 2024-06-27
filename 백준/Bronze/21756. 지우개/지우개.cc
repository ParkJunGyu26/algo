#include <iostream>
#include <vector>

using namespace std;

int main()
{
	int n;
	cin >> n;

	vector<int> num;
	for (int i = 1; i <= n; i++)
		num.push_back(i);

	while (num.size() > 1) {
		vector<int> tmp;

		for (int i = 0; i < num.size(); i++) {
			if (i % 2 != 0) tmp.push_back(num[i]);
		}

		num = tmp;
	}

	cout << num[0];

	return 0;
}