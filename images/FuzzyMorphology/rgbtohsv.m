function hsv=rgbtohsv(rgb)
hsv = rgb;
[x,y,z] = size(rgb);
for i=1:x
    for j=1:y
        r=cast(rgb(i,j,1),'double');
        g=cast(rgb(i,j,2),'double');
        b=cast(rgb(i,j,3),'double');
        maxi = max([r,g,b]);
        mini = min([r,g,b]);
        if r==maxi
            h=(g-b)/(maxi-mini);
        elseif g==maxi
            h=(2+(b-r))/(maxi-mini);
        elseif b==maxi
            h=(4+(r-g))/(maxi-mini);
        end
        hsv(i,j,1) = h;
        hsv(i,j,2) = (maxi-mini)/maxi;
        hsv(i,j,3) = maxi;
    end
end
            