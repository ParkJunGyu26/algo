#include <iostream>
#include <string>

using namespace std;

int n, m;
string str;

// 슬라이딩윈도우...?
int main() {
	cin >> n >> m >> str;

	string target = "I";
	for (int i = 0; i < n; i++) target += "OI";

	int answer = 0;
	for (int i = 0; i <= m-target.size(); i++) {
		if (str.substr(i, target.size()) == target) answer++;
	}

	cout << answer;
}

// 7개인데, 탐색 수는 3개라면 -> 7 - 3 + 1
// 0 1 2 3 4 5 6