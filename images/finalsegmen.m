clear;
clc;
close all;

path(path,'.\FuzzyMorphology');

im = imread(uigetfile ({'*.jpg;*.jpeg;*.tif;*.ppm'}));
I = imcrop(im, [5 145 345 145]);

%I = imread('test4.jpg');
S = imread('D:\PENELITIAN\2014\PUPT\SE_grey.jpg');

A=rgb2hsv(I);%konversi citra rgb ke hsv sekaligus fuzzifikasi
A2=A(:,:,2); %A2 merupakan komponen Saturation dari citra
A3=A(:,:,3); %A3 merupakan komponen Value dari citra

B = rgb2hsv(S);
B1=B(:,:,1);
B2=B(:,:,2); %A2 merupakan komponen Saturation dari citra

%======================= segmentasi citra==============================
[m,n] = size(B1);
B2=ones(m,n)-B2;
sigma = 0.6;
E2 = 1/sigma * B2;
F1 =DilasiKD(A2,E2);

level = 0.51;
F3 = im2bw(F1,level);

segment_citra = imdilate(F3,strel('diamond',3));

F4 = bwlabel(segment_citra);

s = regionprops(F4,'all');
sArea = [s.Area];
idx = find(sArea > 100 & sArea < 800);
s1=s(idx);

figure
imshow(I);
title('Vehicle Detection System');
hold on;
for k = 1 : numel(s1)
        jml(k)=(round(s1(k).Area ));          
        rectangle('Position', s1(k).BoundingBox, ...
            'EdgeColor','r');
        text(s1(k).Centroid(1),s1(k).Centroid(2), ...
            sprintf('%d',k), ...
            'EdgeColor','b','Color','b');    
end





