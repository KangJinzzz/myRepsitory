#define _CRT_SECURE_NO_WARNINGS 1
#include"game.h"

int main()
{
	srand((unsigned int)time(NULL));
	int input = 0;

	do
	{
		menu();
		scanf("%d", &input);
		switch (input)
		{
		case 1:
			game();
			break;
		case 0:
			break;
		default:
			printf("输入错误，请重新选择：\n");
			break;
		}
	} while (input);
	system("pause");
	return 0;
}
