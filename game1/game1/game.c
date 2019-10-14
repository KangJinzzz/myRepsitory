#define _CRT_SECURE_NO_WARNINGS 1
#include"game.h"

void menu()
{
	printf("=====================\n");
	printf("=====  1.play   =====\n");
	printf("=====  0.exit   =====\n");
	printf("=====================\n");
}

void Printboard(char board[ROW][COL], int row, int col)
{
	int i, j;
	for (i = 0; i < row; i++)
	{
		for (j = 0; j < col; j++)
		{
			printf(" %c ",board[i][j]);
			if (j <col - 1)
			{
				printf("|");
			}
		}
		printf("\n");
		
		for (j = 0; j < col; j++)
		{
			if (i < col - 1)
			{
				if ((j < row - 1))
					printf("---|");
				else
					printf("---");
			}
		}
		printf("\n");
	}
}

void Initboard(char board[ROW][COL], int row, int col)
{
	memset(&board[0][0], ' ', row*col);
}

void palyer_move(char board[ROW][COL], int row, int col)
{
	int x, y;
	while (1)
	{
		printf("输入要走的坐标:>");
		scanf("%d%d", &x, &y);
		if (x >= 1 && x <= ROW && y >= 1 && y <= COL && board[x - 1][y - 1]==' ')
		{
			board[x - 1][y - 1] = '*';
			break;
		}
		else
			printf("非法输入！");
	}
}

void computer_move(char board[ROW][COL], int row, int col)
{
	int x, y;
	while (1)
	{
		x = rand() % 3 + 1;
		y = rand() % 3 + 1;
		if (x >= 1 && x <= ROW && y >= 1 && y <= COL && board[x - 1][y - 1] == ' ')
		{
			board[x - 1][y - 1] = '#';
			break;
		}
	}
}


char Judge(char board[ROW][COL], int row, int col)
{
	int i, j;
	int count2 = 0;
	int count4 = 0;
	int count5 = 0;
	int count6 = 0;
	int count7 = 0;
	int k = row - 1;
	int k1 = row - 1;

	//判断玩家赢条件1
	for (i = 0; i < row; i++)
	{
		for (j = 0; j < col; j++)
		{
			if ((board[i][j]) == '*' && (i == j))
			{
				count2++;
			}
		}
	}
	if (count2 == row)
	{
		return '*';
	}
	//判断电脑赢条件1
	for (i = 0; i < row; i++)
	{
		for (j = 0; j < col; j++)
		{
			if (board[i][j] == '#' && i == j)
			{
				count5++;
			}
		}
	}
	if (count5 == row)
	{
		return '#';
	}
	//判断玩家赢条件2
	for (i = 0; i < row; i++)
	{
		if (board[i][k] == '*')
		{
			count4++;
		}
		k--;
	}
	if (count4 == row)
	{
		return '*';
	}
	////判断电脑赢条件2
	for (i = 0; i < row; i++)
	{
		if (board[i][k1] == '#')
		{
			count7++;
		}
		k1--;
	}
	if (count7 == row)
	{
		return '#';
	}
	//判断玩家赢条件3

	for (i = 0; i < row; i++)
	{
		int count = 0;
		for (j = 0; j < col; j++)
		{
			if (board[i][j] == '*')
			{
				count++;
			}

		}
		if (count == row)
		{
			return '*';
		}
	}
	//判断电脑赢条件3
	for (i = 0; i < row; i++)
	{
		int count = 0;
		for (j = 0; j < col; j++)
		{
			if (board[i][j] == '#')
			{
				count++;
			}

		}
		if (count == row)
		{
			return '#';
		}
	}

	//判断玩家赢条件4

	for (j = 0; j < col; j++)
	{
		int count1 = 0;
		for (i = 0; i < row; i++)
		{
			if (board[i][j] == '*')
			{
				count1++;
			}
		}
		if (count1 == col)
		{
			return '*';
		}
	}
	//判断电脑赢条件4
	for (j = 0; j < col; j++)
	{
		int count1 = 0;
		for (i = 0; i < row; i++)
		{
			if (board[i][j] == '#')
			{
				count1++;
			}
		}
		if (count1 == col)
		{
			return '#';
		}
	}
	//判断平局
	int count3 = 0;
	for (i = 0; i < row; i++)
	{
		for (j = 0; j < col; j++)
		{
			if (board[i][j] != ' ')
				count3++;
		}
	}

	if (count3 == (row*col))
		return 'p';
	else
		return 'c';
}



void game()
{
	char ret = 0;
	char board[ROW][COL] = { 0 };
	Initboard(board, ROW, COL);
	Printboard(board,ROW,COL);

	while (1)
	{
		palyer_move(board, ROW, COL);	//玩家落子
		Printboard(board, ROW, COL);  //每次落子后都需打印棋盘
		if ((ret = Judge(board, ROW, COL))!='c')  //这里进行判断游戏是否产生了结果需要结束，游戏结束有三种情况，1.玩家赢 2.电脑赢 3.平局，都不是继续游戏
		{									   
			break;
		}
		computer_move(board, ROW, COL);  //电脑落子
		Printboard(board, ROW, COL);
		if ((ret = Judge(board, ROW, COL)) != 'c')
		{
			break;
		}
	}
	if (ret == '*')
	{
		printf("玩家赢！\n");
	}
	else if (ret == '#')
	{
		printf("电脑赢！\n");
	}
	else if (ret == 'p')
	{
		printf("平局！\n");
	}
}