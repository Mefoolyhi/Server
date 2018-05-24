package com.example.admin.fence.parser;

import com.example.admin.fence.entity.Theatre;
import com.example.admin.fence.repository.TheatreRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public class TheatreParser {




    public void parse(TheatreRepository repository){


        try

        {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://afisha.yandex.ru/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();

            Server service = retrofit.create(Server.class);
            Call<String> call = service.getNews(20,0);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {


                    try {
                        JSONObject js = new JSONObject(response.body());

            JSONArray friends = js.getJSONArray("items");
            for (int i = 0; i < friends.length(); i++) {
                JSONObject friend = friends.getJSONObject(i);


                String id = friend.getString("id");
                String title = friend.getString("title");
                String address = friend.getString("address");
                String image;
                try {
                    image = friend.getJSONObject("logo").getJSONObject("touchPlaceCover").getString("url");
                } catch (Exception e){
                    image  = "http://www.liceoitaliano.net/wp-content/uploads/2014/07/teatro-150x150.png";
                }
                Double latitude = friend.getJSONObject("coordinates").getDouble("latitude");
                Double longitude = friend.getJSONObject("coordinates").getDouble("longitude");



                JSONArray links = friend.getJSONArray("links");
                String link = "";
                for (int j = 0; j < links.length(); j++) {
                    link += links.getString(j) + " ";
                }

                String number = "";
                JSONArray phones = friend.getJSONArray("phones");
                for (int j = 0; j < phones.length(); j++) {
                    JSONObject help = phones.getJSONObject(j);
                    JSONArray numbers = help.getJSONArray("numbers");

                    for (int z = 0; z < numbers.length(); z++) {
                       number += numbers.getString(z) + " ";
                    }

                }





                Theatre e = new Theatre(id,title,address,image,link,number,latitude,longitude);
                 repository.saveAndFlush(e);



                }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    t.printStackTrace();
                }
            });




        } catch(
                Exception e)

        {
            e.printStackTrace();


        }

    }



    public interface Server {

        @Headers({"Host: afisha.yandex.ru", "Cache-Control: max-age=0", "Connection: keep-alive", "Upgrade-Insecure-Requests: 1",
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36",
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8", "Accept-Encoding: utf-8", // gzip, deflate, br
                "Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7",
                "Referer: https://afisha.yandex.ru/showcaptcha?retpath=https%3A//afisha.yandex.ru/api/events/selection/all-events-theatre%3Fcity%3Dyekaterinburg%26limit%3D12%26offset%3D0%26hasMixed%3D0_9558ae93208125d04e3a2fc58bf00e6c&t=0/1527136139/eb2e36117814eafcff27cc26d90e26d2&s=544b85643ca56650af4a493b429e455c",
                "Cookie: yandexuid=8197849441525188113;_ym_uid=1525188124524026287;mda=0;" +
                        "afisha.sid=s%3A7pU9N8DtoWNyuHCTgFYEEwl_guBA5Uso.mWv51ObwY7tyNB%2FjPufByLqrgXZEfLJASfq2Kdb0DZI;bltsr=1;yandex_gid=54;" +
                        "my=YwA=;i=UBZ9pT6he3iss592N9zZQCw080wyeOuMlwVPRwY+1/YU+d1fnf4wkx3RqctlTKKYbtXbq2fnI2P/0hKWgniavkD36Nk=;" +
                        "L=Vg9RXEZadlNYeXpzZAEMaAsIcU5SAFlmLBc3H1c0OD8H.1525354348.13488.326183.ab78f0d5d0c19ab1bddc34ff17bcc864;" +
                        "yandex_login=mefoolyhi;spravka=dD0xNTI3MTM2MTcwO2k9NS4xODkuODUuMjI3O3U9MTUyNzEzNjE3MDAxMDgxMTQ0NjtoPWQwZjU0NTY5YzBiMjNlYjkzMWRlMjUzYTkxYTIyMjBh;yc=1526742973.cb.1%3A1;" +
                        "zm=m-white_bender.gen.webp.css-https%3Awww_BqrpLWAhR02i0GojaLgOLuO-XAU%3Al;"
                        +
                        "yabs-frequency=/4/0G000C6Yw5e00000/UKgmSB0l87nAi72mBo7pJR1mZo_y____b4gmSB0l8FHDi72mBs3NF79mZo_y____/;" +
                        "yp=1840548113.yrts.1525188113#1527787442.ygu.1#1527260087.ysl.1#1540963446.szm.1_25%3A1536x864%3A1536x759#1527834541.shlos.1#1527834541.los.1#1527834541.losc.0#1556778527.p_sw.1525242526#1528280945.csc.2#1556778550.p_cl.1525242549#1840602745.multib.1#1533033826.ww.1#1840714348.udn.cDptZWZvb2x5aGk%3D#1526136941.nps.419942753%3Aclose;" +
                        // "_csrf=At2gGbzMVeu3z3Sst5tZZuuY;Session_id=3:1526232490.5.0.1525354348616:41W9BQ:4c.1|611113175.0.2|181558.958112.kMYogPyY7o6KIC0E6IfZv52LPB0;" +
                        // "sessionid2=3:1526232490.5.0.1525354348616:41W9BQ:4c.1|611113175.0.2|181558.718284.X4ru5DVClyAw0oma20lfMaup0uA;" +
                        "device_id=\"af1b6155987aedc0f38b497e23531f39d67ad9300\";_ym_isad=1"
                        + "_ym_visorc_10630330=w"
        })
        @GET("api/events/theatre/places?city=yekaterinburg")
        Call<String> getNews(@Query("limit") int categoryId, @Query("offset") int offset);
    }



}
