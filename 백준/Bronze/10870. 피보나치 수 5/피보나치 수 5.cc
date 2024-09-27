#include <iostream>

using namespace std;

int Fibo(int number) {
	if (number <= 0) return 0;
	if (number == 1) return 1;
	return Fibo(number-1) + Fibo(number-2);
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	int n;
	cin >> n;
	cout << Fibo(n);
}