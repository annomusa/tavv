clear;
clc;
close all;

im = imread(uigetfile ({'*.jpg;*.jpeg;*.tif;*.ppm'}));
%crop = imcrop(im, [5 145 345 145]);
crop = im;
g = rgb2gray(crop);
imt = im2bw(g, graythresh(g));
baru = bwareaopen(imt,2);
baru = imopen(baru,strel('disk',1));

[imlabel objnum] = bwlabel(baru);

stats = regionprops(imlabel,'all');
ncol = 0;
for i=1:objnum
   stats(i);
   ncol = ncol + 1;
   fitur.data_area(ncol,:) = stats(i).Area;
   fitur.data_index(ncol,:) = stats(i);
   fitur.data_centroid(ncol,:) = stats(i).Centroid;
   fitur.data_obj(ncol) = 1;
end

mobil=0;
ncol=0;
for j=1:objnum-1
    ncol = ncol+1;
    if fitur.data_obj(ncol)==1
        for k=1:objnum-ncol
            if fitur.data_obj(ncol+k)==1
                jarakX = fitur.data_centroid(ncol+k,1)-fitur.data_centroid(ncol,1);
                jarakY = fitur.data_centroid(ncol+k,2)-fitur.data_centroid(ncol,2);
                jarak(j) =sqrt(jarakX^2+jarakY^2);
                if jarak(j)<12
                    fitur.data_obj(ncol)=0;
                    fitur.data_obj(ncol+k)=0;
                    mobil=mobil+1;
                end 
            end
        end
    end
end

for m=1:objnum
    if fitur.data_obj(m)==1 && fitur.data_area(m)>20
        mobil = mobil+1;
    end
end

mobil

figure,
title('Vehicle Detection System');
subplot(3,2,1); imshow(im);
subplot(3,2,2); imshow(crop);
subplot(3,2,3); imshow(imt);
subplot(3,2,4); imshow(baru);
subplot(3,2,5); imshow(crop);
hold on;
for k = 1 : objnum
        rectangle('Position', stats(k).BoundingBox, ...
            'EdgeColor','r');
end