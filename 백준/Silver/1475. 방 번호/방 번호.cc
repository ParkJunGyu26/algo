#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	
	string n;
	int num[10] = {0};
	cin >> n;

	for (int i = 0; i < n.size(); i++) {
		if (n[i] == '9' || n[i] == '6') {
			if (num[6] < num[9]) num[6]++;
			else num[9]++;
		} else {
			num[n[i]-'0']++;
		}
	}

	cout << *max_element(num, num+10);
}