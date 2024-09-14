#include <iostream>
#include <string>

using namespace std;

int main() {
	double cnt = 0.0;
	double total = 0.0;
	for (int i = 0; i < 20; i++) {
		string name;
		double score; // 학점
		string rank; // 등급

		cin >> name >> score >> rank;

		if (rank == "P") continue;

		if (rank == "A+") total += (4.5 * score);
		if (rank == "A0") total += (4.0 * score);
		if (rank == "B+") total += (3.5 * score);
		if (rank == "B0") total += (3.0 * score);
		if (rank == "C+") total += (2.5 * score);
		if (rank == "C0") total += (2.0 * score);
		if (rank == "D+") total += (1.5 * score);
		if (rank == "D0") total += (1.0 * score);
		cnt += score;
	}

	cout << total / cnt;
}