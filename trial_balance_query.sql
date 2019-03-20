USE [EMR]
GO

SELECT TOP 50 GL_balancing.gl_account_no, GL_setup_table.name, GL_balancing.organisation_id AS branch_id, curr_balance
 FROM GL_balancing 
 INNER JOIN GL_setup_table 
 ON GL_balancing.gl_account_no = GL_setup_table.account_no

DECLARE @cnt INT = 2;
WHILE @cnt < 4
BEGIN
DECLARE @credit float
DECLARE @debit float
SELECT @credit=SUM(curr_balance) 
FROM [EMR].[dbo].[GL_balancing] WHERE organisation_id = @cnt AND (gl_account_no LIKE '2%' OR gl_account_no LIKE '3%' OR gl_account_no LIKE '4%') ;

SELECT @debit=SUM(curr_balance)
FROM [EMR].[dbo].[GL_balancing] WHERE organisation_id = @cnt AND (gl_account_no LIKE '1%' OR gl_account_no LIKE '5%') ;
SELECT @credit AS CREDIT,@debit  as DEBIT, @debit - @credit AS DIFF, @cnt AS BRANCH_ID;
   SET @cnt = @cnt + 1;
END;



SET @credit=0
SET @debit=0
SELECT @credit=SUM(curr_balance) 
FROM [EMR].[dbo].[GL_balancing] WHERE (gl_account_no LIKE '2%' OR gl_account_no LIKE '3%' OR gl_account_no LIKE '4%') ;
SELECT @debit=SUM(curr_balance)
FROM [EMR].[dbo].[GL_balancing] WHERE (gl_account_no LIKE '1%' OR gl_account_no LIKE '5%') ;
SELECT @credit AS T_CREDIT,@debit  as T_DEBIT, @debit - @credit AS T_DIFF

