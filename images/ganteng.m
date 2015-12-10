% ganteng pol
clear all; close all
x = imread(uigetfile ({'*.jpg;*.jpeg;*.tif;*.ppm'}));
figure; imshow (x);

% level 1 begin
[xar, xhr, xvr, xdr] = dwt2(x(:,:,1),'haar');
[xag, xhg, xvg, xdg] = dwt2(x(:,:,2),'haar');
[xab, xhb, xvb, xdb] = dwt2(x(:,:,3),'haar');

xa(:,:,1) = xar; xa(:,:,2) = xag; xa(:,:,3) = xab;
xh(:,:,1) = xhr; xh(:,:,2) = xhg; xh(:,:,3) = xhb;
xv(:,:,1) = xvr; xv(:,:,2) = xvg; xv(:,:,3) = xvb;
xd(:,:,1) = xdr; xd(:,:,2) = xdg; xd(:,:,3) = xdb;

X1 = [ xa*0.003 log10(xv)*0.3; log(xh)*0.3 log10(xd)*0.3];
%figure; imshow(X1);

% level 2 begin
[xaar, xahr, xavr, xadr] = dwt2(xa(:,:,1),'haar');
[xaag, xahg, xavg, xadg] = dwt2(xa(:,:,2),'haar');
[xaab, xahb, xavb, xadb] = dwt2(xa(:,:,3),'haar');

xaa(:,:,1) = xaar; xaa(:,:,2) = xaag; xaa(:,:,3) = xaab;
xah(:,:,1) = xahr; xah(:,:,2) = xahg; xah(:,:,3) = xahb;
xav(:,:,1) = xavr; xav(:,:,2) = xavg; xav(:,:,3) = xavb;
xad(:,:,1) = xadr; xad(:,:,2) = xadg; xad(:,:,3) = xadb;

X2 = [ xaa*0.003 log10(xav)*0.3; log(xah)*0.3 log10(xad)*0.3];
%figure; imshow(X2);

% level 3 begin
[xaaar, xaahr, xaavr, xaadr] = dwt2(xaa(:,:,1),'haar');
[xaaag, xaahg, xaavg, xaadg] = dwt2(xaa(:,:,2),'haar');
[xaaab, xaahb, xaavb, xaadb] = dwt2(xaa(:,:,3),'haar');

xaaa(:,:,1) = xaaar; xaaa(:,:,2) = xaaag; xaaa(:,:,3) = xaaab;
xaah(:,:,1) = xaahr; xaah(:,:,2) = xaahg; xaah(:,:,3) = xaahb;
xaav(:,:,1) = xaavr; xaav(:,:,2) = xaavg; xaav(:,:,3) = xaavb;
xaad(:,:,1) = xaadr; xaad(:,:,2) = xaadg; xaad(:,:,3) = xaadb;

X3 = [ xaaa*0.003 log10(xaav)*0.3; log(xaah)*0.3 log10(xaad)*0.3];
%figure; imshow(X3);

% level 4 begin
[xaaaar, xaaahr, xaaavr, xaaadr] = dwt2(xaaa(:,:,1),'haar');
[xaaaag, xaaahg, xaaavg, xaaadg] = dwt2(xaaa(:,:,2),'haar');
[xaaaab, xaaahb, xaaavb, xaaadb] = dwt2(xaaa(:,:,3),'haar');

xaaaa(:,:,1) = xaaaar; xaaaa(:,:,2) = xaaaag; xaaaa(:,:,3) = xaaaab;
xaaah(:,:,1) = xaaahr; xaaah(:,:,2) = xaaahg; xaaah(:,:,3) = xaaahb;
xaaav(:,:,1) = xaaavr; xaaav(:,:,2) = xaaavg; xaaav(:,:,3) = xaaavb;
xaaad(:,:,1) = xaaadr; xaaad(:,:,2) = xaaadg; xaaad(:,:,3) = xaaadb;

X4 = [ xaaaa*0.003 log10(xaaav)*0.3; log(xaaah)*0.3 log10(xaaad)*0.3];
%figure; imshow(X4);

% level 5 begin
[xaaaaar, xaaaahr, xaaaavr, xaaaadr] = dwt2(xaaaa(:,:,1),'haar');
[xaaaaag, xaaaahg, xaaaavg, xaaaadg] = dwt2(xaaaa(:,:,2),'haar');
[xaaaaab, xaaaahb, xaaaavb, xaaaadb] = dwt2(xaaaa(:,:,3),'haar');

xaaaaa(:,:,1) = xaaaaar; xaaaaa(:,:,2) = xaaaaag; xaaaaa(:,:,3) = xaaaaab;
xaaaah(:,:,1) = xaaaahr; xaaaah(:,:,2) = xaaaahg; xaaaah(:,:,3) = xaaaahb;
xaaaav(:,:,1) = xaaaavr; xaaaav(:,:,2) = xaaaavg; xaaaav(:,:,3) = xaaaavb;
xaaaad(:,:,1) = xaaaadr; xaaaad(:,:,2) = xaaaadg; xaaaad(:,:,3) = xaaaadb;

X5 = [ xaaaaa*0.003 log10(xaaaav)*0.3; log(xaaaah)*0.3 log10(xaaaad)*0.3];
%figure; imshow(X5);
figure; imshow(xa/255);


