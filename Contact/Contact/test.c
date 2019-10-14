#define _CRT_SECURE_NO_WARNINGS 1
#include<stdio.h>
#include"Contact.h"
void menu()
{
	printf("============1.add============\n");
	printf("============2.search=========\n");
	printf("============3.del============\n");
	printf("============4.show===========\n");
	printf("============5.modify=========\n");
	printf("============6.clear==========\n");
	printf("============7.sort===========\n");
	printf("============8.destory========\n");
	printf("============0.Exit===========\n");
}

void start()
{
	int input = 0;
	Contact con;
	InitContact(&con);
	do
	{
		menu();
		printf("«Î ‰»Îπ¶ƒ‹£∫\n");
		scanf("%d", &input);
		switch (input)
		{
		case add:
			AddContact(&con);
			break;
		case search:
			SearchContact(&con);
			break;
		case del:
			DelContact(&con);
			break;
		case show:
			ShowContact(&con);
			break;
		case modify:
			ModifyContact(&con);
			break;
		case clear:
			ClearContact(&con);
			break;
		case sort:
			SortContact(&con);
			break;
		case destory:
			Save(&con);
			DestoryContact(&con);
			break;
		default:
			break;
		}
	} while (input);

}
int main()
{
	start();
	system("pause");
	return 0;
}