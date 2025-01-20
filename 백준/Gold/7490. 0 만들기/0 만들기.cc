#include <iostream>
#include <vector>
#include <string>
#include <queue>

using namespace std;

int t, n;
string cal[3] = {" ", "+", "-"};

int SUM(vector<string> &hubo) {
	queue<string> numbers;
	queue<string> calculator;

	string now = "";
	for (int i = 0; i < hubo.size(); i++) {
		if (hubo[i] == "+" || hubo[i] == "-") {
			numbers.push(now);
			calculator.push(hubo[i]);
			now = "";
		} else if (hubo[i] != " ") {
			now += hubo[i];
		}
	}

	if (now.size() != 0) numbers.push(now);

	int now_num = stoi(numbers.front());
	numbers.pop();
	
	while (!numbers.empty()) {
		int target = stoi(numbers.front());
		if (calculator.front() == "+") {
			now_num += target;
		} else {
			now_num -= target;
		}
		calculator.pop();
		numbers.pop();
	}

	return now_num;
}

void dfs(vector<string> &num, vector<string> &hubo, int index) {
	if (index == n-1) {
		hubo.push_back(num[index]);
		
		if (SUM(hubo) == 0) {
			for (auto h : hubo) cout << h;
			cout << "\n";
		}
		hubo.pop_back();
		return;
	}

	// 숫자 추가
	for (int i = index; i < n; i++) {
		hubo.push_back(num[i]);
		
		// 연산 추가
		for (int j = 0; j < 3; j++) {
			hubo.push_back(cal[j]);
			dfs(num, hubo, index+1);
			hubo.pop_back();
		}

		hubo.pop_back();
		return;
	}
}

// 더하기, 빼기, 합치기
// 숫자들은 위치가 고정
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> t;

	for (int _ = 0; _ < t; _++) {
		cin >> n;
		vector<string> num;
		for (int i = 1; i <= n; i++) num.push_back(to_string(i));

		vector<string> hubo;
		dfs(num, hubo, 0);
		cout << "\n";
	}
}


/*


1 + 2 + 3 -> 1 + 2 
1 + 2 - 3
1 + 2   3

1 

*/