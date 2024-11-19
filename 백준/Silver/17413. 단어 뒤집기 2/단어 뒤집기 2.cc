#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	string str;
	getline(cin, str);

	string target = "";
	string answer = "";
	bool check = false;

	for (int i = 0; i < str.size(); i++) {
		if (str[i] == '<') {
			check = true;
			if (target.size() > 0) {
				reverse(target.begin(), target.end());
				answer += target;
				target = "";
			}
		}

		if (str[i] == '>') {
			check = false;
			answer += str[i];
			continue;
		}

		if (str[i] == ' ') {
			reverse(target.begin(), target.end());
			answer += target;
			answer += str[i];
			target = "";
			continue;
		}

		if (check) answer += str[i];
		else target += str[i];
	}

	if (target.size() > 0) {
		reverse(target.begin(), target.end());
		answer += target;
	}

	cout << answer;
}