#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

int n, k;
string target = "";
vector<char> answer;

void make_binary(int num) {
	string result = "";
	while (true) {
		if (num / 2 == 0) {
			result += to_string(num % 2);

			reverse(result.begin(), result.end());
			target += result;
			return;
		}

		result += to_string(num % 2);
		num /= 2;
	}
}

int main() {
	ios_base::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);

	cin >> n >> k;
	--k;
	int index = 0;
	while (target.size() <= n * 5) {
		make_binary(index++);
	}

	index = 0;
	while (answer.size() < 5) {
		if (index % n == k) answer.push_back(target[index]);
		index++;
	}

	for (auto ans : answer) cout << ans << " ";
}