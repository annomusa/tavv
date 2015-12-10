function hasil=Perluas(f,p,l,t)
[m,n] = size(f);
hasil = zeros(m+2*p,n+2*l);

for i=1:(m+2*p)
    for j=1:(n+2*l)
        if i<=p || j<=l || i>m+p || j>n+l
            hasil(i,j)=t;
        else
            hasil(i,j)=f((i-p),(j-l));
        end
    end
end
end
            
            