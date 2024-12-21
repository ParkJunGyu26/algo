#include <iostream>

using namespace std;

class Node {
public:
    int data;
    Node* left = NULL;
    Node* right = NULL;

    Node(int num) {
        data = num;
    }
};

void set_node(Node& root, Node* node); // 매개변수를 Node*로 수정
void set_right(Node& root, Node* node); // 매개변수를 Node*로 수정
void set_left(Node& root, Node* node); // 매개변수를 Node*로 수정

// 크기 비교
void set_node(Node& root, Node* node) {
    if (root.data < node->data) set_right(root, node);
    else set_left(root, node);
}

void set_right(Node& root, Node* node) {
    if (root.right == NULL) root.right = node;
    else set_node(*root.right, node); // 기존 자식 노드를 기준으로 재귀 호출
}

void set_left(Node& root, Node* node) {
    if (root.left == NULL) root.left = node;
    else set_node(*root.left, node); // 기존 자식 노드를 기준으로 재귀 호출
}

void solution(Node& node) {
    if (node.left != NULL) solution(*node.left);
    if (node.right != NULL) solution(*node.right);
    cout << node.data << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int number;
    cin >> number;
    Node* root = new Node(number); // 루트 노드 생성

    while (cin >> number) {
        Node* node = new Node(number); // 입력받은 값으로 새 노드 생성
        set_node(*root, node); // 포인터를 전달
    }

    solution(*root); // 트리 출력
    return 0;
}
