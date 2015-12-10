function hasil=DilasiKD(f,B)
hasil=f;
[m,n] = size(f);
[o,p] = size(B);
temp = Perluas(f,floor(o/2),floor(p/2),0);

for i=1:m
    for j=1:n
        pembanding = temp(i:(i+o-1),j:(j+p-1));
        hasil(i,j) = DKD(pembanding,B);
    end
end
end

%hasil = DKD(temp,B);
