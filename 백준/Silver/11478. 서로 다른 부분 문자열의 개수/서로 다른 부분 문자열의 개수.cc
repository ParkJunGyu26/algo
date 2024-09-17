#include <iostream>
#include <string>
#include <set>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	string S;
	cin >> S;

	set<char> s;
	set<string> ss;
	
	for (int i = 0; i < S.size(); i++) { // 인덱스 위치
		string tmp = "";
		for (int j = i; j < S.size(); j++) {
			tmp += S[j];
			ss.insert(tmp);
		}
	}

	cout << ss.size();
}