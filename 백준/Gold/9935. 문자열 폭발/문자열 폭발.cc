#include <iostream>
#include <string>

using namespace std;

string s, bomb;

int main() {
	ios_base::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);

	cin >> s >> bomb;
	string answer = "";

	for (int i = 0; i < s.size(); i++) {
		answer += s[i];


		while (!answer.empty() && answer.size() >= bomb.size()) {
			if (answer.substr(answer.size()-bomb.size(), bomb.size()) == bomb) {
				for (int j = 0; j < bomb.size(); j++) answer.pop_back();
			} else break;
		}
	}

	if (answer.empty()) cout << "FRULA";
	else cout << answer;
}