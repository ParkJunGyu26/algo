#include <iostream>
#include <string>
#include <stack>
#include <vector>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	vector<char> mo = {'a', 'e', 'i', 'o', 'u'};
	string str;
	bool condition1, condition2, condition3;

	while (true) {
		stack<char> s;
		condition1 = condition2 = condition3 = false;
		cin >> str;
		if (str == "end") break;

		s.push(str[0]);

		// 조건3
		for (int i = 1; i < str.size(); i++) {
			if (s.top() == str[i]) {
				if (str[i] == 'e' || str[i] == 'o') continue;
				else {
					cout << "<" << str << "> is not acceptable.\n";
					condition1 = true;
					break;
				}
			}
			s.push(str[i]);
		}

		if (condition1) continue;

		int check1, check2;
		check1 = check2 = 0;
		// 조건2
		for (int i = 0; i < str.size(); i++) {
			if (!(str[i] == 'a' || str[i] == 'e' || str[i] == 'i' || str[i] == 'o' || str[i] == 'u')) {
				if (check1 > 0) check1 = 0;
				check2++;
			} else {
				condition3 = true;
				if (check2 > 0) check2 = 0;
				check1++;
			}

			if (check1 >= 3 || check2 >= 3) {
				cout << "<" << str << "> is not acceptable.\n";
				condition2 = true;
				break;
			}
		}

		if (condition2) continue;

		if (condition3) cout << "<" << str << "> is acceptable.\n";
		else cout << "<" << str << "> is not acceptable.\n";
	}
}