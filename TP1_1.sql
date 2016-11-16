Select * From terrain where type='S';
Select * From abonne where parrain Is Null Order By nom;
Select r.* From reservation as r Inner Join terrain as t On t.tno=r.tno
       where t.type='S';
Select r.rno,r.dater,r.tno,a.nom,a2.nom
       From reservation as r Inner Join terrain as t
       On t.tno=r.tno Inner Join abonne as a2 on r.ano1=a2.ano
       Inner Join abonne  as a on r.ano2=a.ano
       where t.type='S';

Select Distinct ano,nom
       From reservation as r, abonne as a 
       where (a.ano=r.ano1 OR a.ano=r.ano2) AND dater>'2016-09-10 00:00:00'
       	     AND dater<'2016-09-11 00:00:00';
Select r.* From reservation as r Inner Join terrain as t On t.tno=r.tno
       where t.type='S';

Select a1.* From abonne as a1 Inner Join abonne as a2 on a1.ano=a2.parrain;
Select a1.*, a2.nom
       From abonne as a1 Inner Join abonne as a2 on a1.parrain=a2.ano;
