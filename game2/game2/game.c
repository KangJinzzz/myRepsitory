#define _CRT_SECURE_NO_WARNINGS 1
#include"game.h"

void menu()
{
	printf("=====================\n");
	printf("=====  1.play   =====\n");
	printf("=====  0.exit   =====\n");
	printf("=====================\n");
}

void Initboard(char arr[ROWS][COLS], int row, int col, char set)
{
	memset(&arr[0][0], set, row*col);
}

void set_mine(char arr[ROWS][COLS], int row, int col)
{
	int x, y;
	int count = EASY_GOAL;
	while (count)
	{
		x = (rand() % (row-2)) + 1;
		y = (rand() % (col-2)) + 1;
		if (arr[x][y] != '1')
		{
			arr[x][y] = '1';
			count--;
		}
	}
}

void Printboard(char arr[ROWS][COLS], int row, int col)
{
	int i, j;
	for (i = 0; i <= row - 2; i++)
	{
		printf("%2d ", i);
		for (j = 1; j <= col - 2;j++)
		{
			if (i==0)
				printf("%2d ", j);
			else
				printf("%2c ", arr[i][j]);
		}
		printf("\n");
	}
}

void Judge(char mine[ROWS][COLS], char show[ROWS][COLS], int row, int col)
{
	int x, y;
	int count = 0;
	int count1 = 0;
	while (count<ROW*COL-EASY_GOAL)
	{
		count1++;
		printf("输入排查的坐标:>");
		scanf("%d%d", &x, &y);
		if (x >= 1 && x <= ROW && y >= 1 && y <= ROW)
		{
			if ((count1 == 1) && (mine[x][y] == '1'))   //判断第一次选择是否为雷，是则将雷移至别处
			{
				int j, k;
				mine[x][y] = '0';
				while (1)
				{
					j = (rand() % (row - 2)) + 1;
					k = (rand() % (col - 2)) + 1;
					if (mine[j][k] == '0')
					{
						mine[j][k] = '1';
						break;
					}
				}
				
				Unfold(mine, show, x, y);
				Printboard(show, ROWS, COLS);
				count++;
				continue;
			}
			else if (mine[x][y] == '1')
			{
				Printboard(mine, ROWS, COLS);
				printf("游戏失败！\n");
				break;
			}
			else
			{
				Unfold(mine, show, x, y);
				Printboard(show, ROWS, COLS);
				count++;
			}
		}
		else
			printf("坐标非法!");

	}
	if (count == ROW*COL - EASY_GOAL)
	{
		printf("扫雷成功，666！\n");
	}
}

int Count(char arr[ROWS][COLS], int x, int y)
{
 return arr[x - 1][y - 1] +
		arr[x - 1][y] +
		arr[x - 1][y + 1] +
		arr[x][y - 1] +
		arr[x][y + 1] +
		arr[x + 1][y - 1] +
		arr[x + 1][y] +
		arr[x + 1][y + 1] - 8 * '0';
}

void Unfold(char mine[ROWS][COLS], char show[ROWS][COLS], int x, int y)
{
	if (x >= 1 && x <= ROW && y >= 1 && y <= ROW)
	{
		if (Count(mine, x, y) != 0)
		{
			show[x][y] = Count(mine, x, y) + '0';
		}
		if (Count(mine, x, y) == 0)
		{
			show[x][y] = ' ';
			if (show[x-1][y-1]=='*')
				Unfold(mine, show, x - 1, y - 1);
			if (show[x - 1][y] == '*')
				Unfold(mine, show, x - 1, y);
			if (show[x - 1][y + 1] == '*')
				Unfold(mine, show, x - 1, y + 1);
			if (show[x][y - 1] == '*')
				Unfold(mine, show, x, y - 1);
			if (show[x][y + 1] == '*')
				Unfold(mine, show, x, y + 1);
			if (show[x + 1][y - 1] == '*')
				Unfold(mine, show, x + 1, y - 1);
			if (show[x + 1][y] == '*')
				Unfold(mine, show, x + 1, y);
			if (show[x + 1][y + 1] == '*')
				Unfold(mine, show, x + 1, y + 1);
		}

	}
	return 0;
}

void game()
{
	char mine[ROWS][COLS] = { 0 };
	char show[ROWS][COLS] = { 0 };
	Initboard(mine, ROWS, COLS, '0');
	Initboard(show, ROWS, COLS, '*');
	set_mine(mine, ROWS, COLS);
	Printboard(mine, ROWS, COLS);
	printf("\n");
	Printboard(show, ROWS, COLS);
	Judge(mine, show, ROWS, COLS);

}