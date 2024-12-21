#include <iostream>
#include <vector>
using namespace std;

vector<int> preorder;

// 재귀적으로 후위 순회를 출력하는 함수
void postorder(int start, int end) {
    // 더 이상 탐색할 노드가 없으면 종료
    if (start >= end) return;

    int root = preorder[start]; // 현재 서브트리의 루트
    int idx = start + 1; // 루트 다음부터 탐색 시작

    // 오른쪽 서브트리의 시작 지점 찾기
    while (idx < end && preorder[idx] < root) {
        idx++;
    }

    // 왼쪽 서브트리 후위 순회
    postorder(start + 1, idx);
    // 오른쪽 서브트리 후위 순회
    postorder(idx, end);
    // 현재 루트를 출력
    cout << root << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int number;
    // EOF까지 입력 받기
    while (cin >> number) {
        preorder.push_back(number);
    }

    // 전체 범위에 대해 후위 순회
    postorder(0, preorder.size());

    return 0;
}
