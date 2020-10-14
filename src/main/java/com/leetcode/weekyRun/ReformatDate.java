package com.leetcode.weekyRun;

import java.lang.reflect.Array;
import java.util.*;

public class ReformatDate {

    public String reformatDate(String date) {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        Map<String, String> monthMap = new HashMap<>();
        for (int i = 0; i < months.length; i++) {
            String s = i < 9 ? "0" + Integer.toString(i + 1) : Integer.toString(i + 1);
            monthMap.put(months[i], s);
        }
        String[] dateArray = date.split(" ");
        StringBuilder reformatDate = new StringBuilder();
        for (int i = dateArray.length - 1; i >= 0 ; i--) {
            switch (i) {
                case 2:
                    reformatDate.append(dateArray[i]);
                    break;
                case 1:
                    reformatDate.append(monthMap.get(dateArray[i]));
                    break;
                case 0:
                    String day = dateArray[i].length() == 3 ? "0" + dateArray[i].substring(0, 1) : dateArray[i].substring(0, 2);
                    reformatDate.append(day);
                    break;
                default:
                    break;
            }
            if (i != 0) reformatDate.append("-");
        }
        return reformatDate.toString();
    }

    private final int MOD = 1000000007;
    public int rangeSum(int[] nums, int n, int left, int right) {
        int[] sums = new int[n * (n + 1) / 2];
        int p = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = i;
            int loc = p - count;
            sums[p++] = nums[i];
            while (count != 0) {
                sums[p++] += (nums[i] + sums[loc++]);
                --count;
            }
        }
        Arrays.sort(sums);
        int result = 0;
        for (int i = left - 1; i <= right - 1; i++) {
            result += sums[i];
            result %= MOD;
        }
        return result;
    }

    public int minDifference(int[] nums) {
        if (nums.length <= 4) return 0;
        Arrays.sort(nums);
        int delta = nums.length - 4;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - delta; i++) {
            if (nums[i + delta] - nums[i] < min) min = nums[i + delta] - nums[i];
        }
        return min;
    }

    public boolean winnerSquareGame(int n) {
        int sqrt = (int)Math.sqrt(n);
        int[] v = new int[sqrt];
        for (int i = 0; i < sqrt; i++) {
            v[i] = (i+1) * (i+1);
        }

        boolean[] result = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int sq : v) {
                if (sq <= i) {
                    if (!result[i - sq]) {
                        result[i] = true;
                        break;
                    }
                }
            }
        }
        return result[n];
    }

    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> numCount = new HashMap<>();
        for (int num : nums) {
            numCount.merge(num, 1, Integer::sum);
        }
        Object[] counts = numCount.values().stream().filter(c -> c > 1).toArray();
        int result = 0;
        for (Object c : counts) {
            int cc = (int) c;
            result += (cc - 1) * cc / 2;
        }
        return result;
    }

    public int numSub(String s) {
        String[] ss = s.split("0");
        int result = 0;
        for (String s1 : ss) {
            if (s1.length() > 0) result += cal(s1.length());
            result %= MOD;
        }
        return result;
    }
    private int cal(int n) {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result += i;
            result %= MOD;
        }
        return result;
    }

    private class Road{
        public int end;
        public double prob;

        public Road(int end, double prob) {
            this.end = end;
            this.prob = prob;
        }
    }
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, ArrayList<Road>> roadsMap = new HashMap<>();
        boolean[][] canGet = new boolean[n][n];
        for (int i = 0; i < edges.length; i++) {
            canGet[edges[i][0]][edges[i][1]] = true;
            canGet[edges[i][1]][edges[i][0]] = true;
            ArrayList<Road> list = new ArrayList<>();
            list.add(new Road(edges[i][1], succProb[i]));
            roadsMap.merge(edges[i][0], list, (l1, l2) -> {
                l1.addAll(l2);
                return l1;
            });
            list = new ArrayList<>();
            list.add(new Road(edges[i][0], succProb[i]));
            roadsMap.merge(edges[i][1], list, (l1, l2) -> {
                l1.addAll(l2);
                return l1;
            });
        }

        ArrayList<Road> list = roadsMap.get(start);
        double max = 0;
        if (list != null) {
            for (Road edge : list) {
                var canGetCopy = canGet.clone();
                // 将目的地为start的边删除
                deleteEdge(canGetCopy, start);
                Stack<Road> stack = new Stack<>();
                stack.push(edge);
                while (!stack.isEmpty()) {
                    Road road = stack.pop();
                    if (road.end == end) {
                        if (road.prob > max) max = road.prob;
                        continue;
                    }
                    ArrayList<Road> roadArrayList = roadsMap.get(road.end);
                    deleteEdge(canGetCopy, road.end);
                    if (roadArrayList != null) {
                        for (Road r : roadArrayList) {
                            if (canGetCopy[road.end][r.end]) {
                                r.prob *= road.prob;
                                stack.push(r);
                            }
                        }
                    }
                }
            }
        }
        return max;
    }

    private void deleteEdge(boolean[][] canGet, int start) {
        for (int i = 0; i < canGet.length; i++) {
            canGet[i][start] = false;
        }
    }

    public static void main(String[] args) {
        ReformatDate reformatDate = new ReformatDate();
//        System.out.println(reformatDate.maxProbability(3, new int[][]{{0,1},{1,2},{0,2}}, new double[]{0.5,0.5,0.2}, 0,2));
        System.out.println(reformatDate.maxNumOfSubstrings("slqdvfkaavwempfoncluddpgxessnxnmmofukhdgwwyrhyfavmostcowgvtglnauzgsonsxvjnyhxuurhyviyqajhxehgxuzbvucwrewiicltidvggyhmprygtsakdszadtyciqigzdiihzvhwfwhmzxmxrzjegjvwvgsvqsdnrnkgvmxxsdlvizqzzkzhrmdbmpuiugrhmzdislbnsdflugeevcdchcmnurwwfpijjpytwliubflujzqtvpriwoxjgvukoklpjgjybjvtpwmmwcwvpqbrniygycvwouejlepsyhhaqtjhgdlgtbfxjckznbaoeswvljtqwmevwbackgkdxwdoimcbpwgklyishmzwtanmvhcbkhykivillnmfudkfjowvbpkdkeltxhzyazwnajjuqcikbqylwbwdbxdgnxbgbhrunptficowbxvndmtcjsuhjrrfnzrgvxevkaaqyagixyqlggknyrvxrjfmzxtlrhkdzvwngeacpnvmdkegvfrlqkjupenwgooofdbxdjyyxoktrchcvceanpcpkpkglohqptygvtpcmbamnjxtmpthqqwjyiqxbzshvkfziqkegawrilbeujwivgxcuqgbrbxmsimmdlfflktwzbqhyvghwzqhrlksczzsnmabbishoshlkkrqkafrqefymgfgutcqifnrkvpegezieafgpdeowggccxhjdthracxpeeztzpsplsdczyursnlaridtxtwgcozlhmphddbuwuhxjogjxbvzqczgfpxoctpuziimgflsqtamigewcghkdwcovkocpgciboanfmjcnykrrydhonbwsqunydmibbidfysecgwhhrkqajbbzqljrjxbysffhsubjseoejsqzhdznjoibzoluemsjhqeqhhxsjbbecjrhzaqqmxuahmqkjqgrthbqysqpafxymcaprghihercihnkqpojoayqdoofggkcvmsxipafcgmlufhguwkfuszmedilobzbpbtruzbdfcgfjayommuhctredluwzwdqfxyujgzuabxvngcreppnontrpygqgtcptisxophrxqqfxdjmtreeprbxpzcazodckheuwjsqwqyupccoexzvuxtmbbmyfhtfcqohccnhjtcjgovuzlnokitubsgxyadczscwyqkhgnpvazhrbpexyblfbmrnvvgwzcxsugdeatjwwecgnxuypysiudwtssmoutcxqmrehyotkjootemjtuqtiglcxbjpwqqfgxzfmoxvgrgkqxohiztqqlfyuqpdiectcqjnxtgkidtdueabjnybitwnjfxvcltgxpoivoledrrauxjhrwqbocdrwfsfjpftyhiutzomabqqdappicpqhwlebglgebxduksavvsjpcanahwfaahcqiviivrnjdbvtbhoosotjdiljzzqxgtytbzvtkpjlpsxxgqrdeivgiemymeraovaclpwwaehizcucfrtxeqspepnmqohkuxjmkmhskxzmujpvnjcsviwsyqqhskrtmzjqcdbbxcyelskblddulwxtnfmunroacmeizoptsbtvagdvtyfzlmzdyjzbqumskzvxkqhvrhpjbnoqjxhtsrcknctqshwvxvkzdkjpguxikxhnnlqlwhdvzxudxevdxvlklrqxsgrhlshofzcacuojyuxzwwkjyadsnboehrjoapgzsqwucjwlsehaywhbsdxaumilqdcsldfluufatxoekchawyoimxphdkorpbqbhtgiogejojxthmrfazodskbkyvohcnorzazxztnchfoeijvzgpspknnrloreablqqeqnvwozefjsedzkwbdpazbesgxzkdcqopajkpmquueuqozuhzaiizxnrxewzbzxxukuooncaueadliluasqaskhweqwueshpaoywkcemtugnespxivlofthvjzmzdezkxggdqpexhqdvqwesoutybbqzsimwqighowjhkgmkqbkcvhmjvxusgoyvkbrilvakcpfafwgardlskuxyoifwbdnobhavuwfmrkmodqswrhtjweodbddvpuhkbthjezpvkdfzeedqakeqavuxpjvwmstmfqczhlnybrsfofvajqlgfdcqwlomlwoyjffgstokilbvupwpeikiruzouqrzyvybwxstdscgrisgcsfwvtnngcipbjbmgcwardzstbfulacfyrfnwuxvjwzwxsgojadifnvqgjecorzaexhjucebbimsgbydfkreifgbiyizerotcoibexypdypvecuuprcdndpmeeegbntpmjplgtjmrmbgowmkchgvxrdwlpsipmnsqnaqiszyycgiqrfwvgacodintvfpsprkefqyteyvxwwuiujbzpqgucieucxtykvvdetbyywvcebwwrlxhabnzbkmjzoburoedhcosesjkaayeyjbhcfukhlmrcmwwkecptmsoosabaxfruvqwzdtwlnuczksnuryoqbhyiqtjvfqfmtobbhoijnafosxsrositydwjahnkerkafxqftqbqubenmgmdfmvbkswcsdmteiqpfhipirqfwthpyngodwyewxjusymvvumpnnluytfoflsmdyaerwrnnfzvnyczghxsuiysblqyeqvnjilmrwwssyitphucalwycxsfocgsxgoxkqidypfolxpchfenlwrkxwmngxitconronrypanhzuxuomknscmtzadbpgtrwqrvnwhfhuvcmzinugqiomkayzwmzcjepnputlcyivtfykyiydmshlrobaekalpwtsfrrvsguriylzrazyqshxaictjfjmafyivbmphlwokepwzhcijzlrorvjxapzenqnxdftvibmfhahzjxrjhdiqcmizgaqmsygmmjehiswpvcuyvyjqpdlnhvuzcslazkwowbcmqybhjqscyjdewrzowgfekroentashukeinrihgmebrtfkrkuvegaikmrxqtjhymrqqdqtrbpzewxyvmzgdsbztioslaqkknuhcyhllvyatxttuhogdldhawpbqqiipspjgbebilgjbdchtizcflwqgdqcfodbmmfzaezexvxxzebncfxqmsviezsynuwcistqnvgkwpvwptrcmatnvjzzpsamhvawkotmisoqtxlzljvgwnvdgxyxorrfcgnoayqagqslfhrquugcqdrksaiyshtxiqufisboqrsxhimnkrpttknthoynjzsnvzepjbycppoacabtasjquwmydrecyxowvjrrirktmbiubhmwwiznwrrtshtoiafltlkgjpwvmfqlbjhhqhecnrapdgoknlnojlcvqwasjnedwrflfssmvbhyaexbqtrzgfctnxchlgwhjgsblnllupolninqgeopgjatsifktypvfonojizlkbrpfdpporvvtzicbideqxwzfsravoajekluxohlsfzmckmhsvcenverdodcklfhqdihonpnrpbmkqlxirslomuwifnbxbmxvyjesgeeyvelamuhwosukgipjigpglyzyusmllbbbofuowvhmygivmqxjvgqidolotavnbtbvyqmnxbnlrttenvlkiklufutihoudcqtyesplfvrveldhjmgxhuehenyujrxjvmipgqomoleundfwqdvdjkaadhkdbgsqimgpmsvarxytzqcklxkpudromnllqwvkvpuurvmqypyprdnzymiaoaimydcdxmkyipmvyhyrtscttfgqpoprqgnsohniqozlpvqdvxnexfuwpheaelikyklcfywelzoqrzslodghxtsdweoptwtrnpavdqpfssmvldgkuypskkgwvghktomjpjqqjpqcfmirpfzkxqgihpbzbjhorqvsekwrgbzktyoubsxnzsptflylverbzgralvhbmtczjeswcercurxkptvcdnpqfedbcjwnzudydrxcffqiycgzntsvjqncfzjfdhmteixfgvckeyqdcyxaratiqogtvobawazapompnuvgelqcuevzcwhjupiqjunyaubboilwkealauazggzuozryrrxbqgfmgpdqxmrjyqveeybhiclsnwherxekfnycvsbyaeqiyniaqftwnctyufoemogcsyxjfeklrngfahycjypzrtxbfszkuszqpbhvabmelqbdcsgmbssibdefucmhvdhfebnqlmupifxtprrlrxvuyrsysnrcygfwtzfjqzpbhavquvqlgnxfliwcrcpdsmcfzwdpiavmmwjyeegbkcwrxfggqujpliaounqisdodgsfrazpukybrsflttovwwasrywsgmowlqlvzcwbxgzkeoeaqwluavzrfrrkqdlkwpeqfglbvexpsowfufpbnzixowshddwibwbveafyzuofajakomvmvwmrlqevvrervinvmlbpdzyqvussvirizimigzfivvjlpyjvmzsudiiviapjindpwxugadltpzmegzklerspayxugwobigzwppyrlcwgdinxjkiiaohtyfgjzgjbsfvqdtdozlozrgfwcxbuvqfdhlscuvgyqhiveywxsigemoicbdrdtynyjxuvpgdoamgenqssxjvxgkpunodcnwqmnlmmwuykgylobeupnztknjoadwtyfqzotzwppxbhcrtzzijizritbvaqimtjnxkzmpxigwcfgkxxnonhybiqvqoafjthqvkyelpmxcetrrwudtlgxvrpaphieawlgeriyeomgdlxtatssugelfayhvjrhfkbosglkfpkqtruqqtlzknwaiojgxmzsodowimwrrzhmszxcapydjysbqkznsewswjhhdypqaawrhnjishijkqwmeojimxpgqpyosqyrmazqawjwbsmlgnesknfmurpsrjhvgrarpeksgcawkhruxynafleeknlboxaeftuulhcqxtjkduiiikuzozdsnsxiaxlymoqbyidbnemdzrlqoucmmgqyrbwnmtxxgsyylqnwexchyaekbiztlwgyylxevvsjrmsslrjxhlujnnkejdalevgeqhroeqiejzqyibmgamguelpmjblnucpfygvtkuhelqsmnmwhdamyjolugtinuijkiaogwicrwuyqjsozzimxbirjbuyugkgkkxctzklhcdegqlqykcekkkqfbnuakmfaelfmryqszqsneqvgylqlusykiyjpmkubodaqzxajbjtyfdaqlbtqkhasgezlfgvidqnvsnilmjycceebvxtyczpkhmxzpgvxxgiglcyhcfapltmqtpkmrariekobrgfbrtltdchahemtoyuljpsstthmqrubgdhlcdomcqylqtpyhiksfyjxtarycjxljeijxntoarczuwpzqllthmtgjnyzpjwdmhcxnflacmtepugsyhxppnssbpzgwqsjqyxyrlekgatilahnvzurrtayyervhwlzsxmtibeuxzyfmtexgredtbjzxacsesssvvedrmzuwgshaakfowcufieuecfgzrgmohmjlulbusxynsbkopwipodrbxfohwzrseleztuvdmxsyhzpdolbmqqekkklryncqfygmqwrybohsllrfocqmnfeefipggvninbfqskcsnnhcrcnpomehbgpaabngbzamgjputokcfkpsmfimeqfefirxmuekvocmhqegeytsinzhakkrgcconqdxyztbusemccusqvclnvzhylvxzsqqxnryvqnwsqrhswvsnpszbovtitptmjkirbktioaoybudnknnwbeksdlvfizjtjszfcdzrxvonjjgihcxzbpcjgbnzworstfzcvphohckclwjjwelgzxiusiabhyhdgmqbsgrngznvtzwqqoymjjvsjhzovhghcvrnsknzjcndzmlhhqpoinmuhxjdtkyeybbkygdjqhmfhdaevxcalhbmebxoqpjdqgojxvokhnjluuzwwdbyvbfhrfwtpdivtszpmeirtponxzbcerepzrejguzjgsnpalxsrmjhgxqxyfchmrrizloidzatxqwqctpantkhpllqqlozvauuvsvmouddpqxesdtyvzbywxcobcnawavebdaoumsfonuogvepmedrrecdfetujggrybyctmdgkoqmojjqqnmbytqtjfmdfmcnztqngasegcikitrtrfrrttjcxerdgregtcyjemmmqbozmhpfxbczibdojbspcxtuephnfoyczrbqarbbynnsenuqupvatijbdljgokvuvybqjdtnqhyeshtmzfzhgmvnlfcphxkwdputgtduongqpczylpurylhdtskpfboezcaomngzpyqhylhsfbdherdmrodrobbkvasewijovkfjyonpypskyhluskmkxmjccddfuqyloqyuropqbkkxhbstencqgdwacehqzemwtstbkqzvbfejcchihidrwgnvymlxoprsmosnnsaqwnrigktizgdqkpxzuolhmzolhzocvedhgnxusgnhvkrjztxqhdjputkqjaabluiavhfgweuabenwtczhazsuqttvkjnlygfyubxzokxeduenpkfdalhojtxwpbxzrakxjdplxqiidelyltkcojmiswvszybmwjsqjrbeddbrwkaapqjqzcjhhutyxgetiqesfnmbgengdzvotzwgfhxrxlzpbxctyhtgtrxwfygpwmpkqkcrkqnzklvwswdbecqlrisebgsuwsnofcuzlyuabbbxjywjbgoaicyeqjihosjmvqyfnzirfqeofnsuggxupxrxksjyrcyvtvuelproevtozvmbmurlhpkhhgkpayorbfulhcucqgcbseeuaqfsmwokvfvbbenvwxyfbupxifssabefecbexcufjrwkwkzhlabjtguunmcemrljxepzafakurmuoiikyczavzbomzasvjdsnobxdbokgbsspaxbzqjrhkybjjyslawzsyznvwoaoiwbkkyloiysmwkjxufzkwebjsunixngmlfpoopzitooqzuniduxvqdhzrjmcszvhqubngtqbhjajupofkkynlwyyvbzqffsslbhfertezkgutwemlgirstsrzpaupzoztrtlydssvvzizowmmywiibogyjbnoefvvdyvysowjbiqftmzpbmtrvbrbnvjxlnkqbiprhlvfnyszgptfbtdejalaqlqebfqriyophczonjcmlwbnyprgcwhafmqxgrlybigchioetgaenucovwjzaybsvjqpqvksnxgoehzvzbjwboiayyizyyzbolrsptbcwogxxvqpidembchieztzyvaxuggkafhafordxivunfvytrymbswycfdexghcqnazsfarsxlydlufjecyorumfqrnwqixwgaenywdrjrjuuuxxupnkjysfejndeyikwxcjufwbhtgkeyqipisicgmusxbbqyolvjboswwnngdznvjdwdnogkfjfixlvmydeghxcyrmgovaprpuynyohojhlzkbytdexgolyhzzvvvgbwtzlydmcanhxrqprzianmycisbqwsnaxoqkdqpwakmwjjqbwjzynzfiituvsedjrsjsisknncmtlikndcvyxfsspvndlzulfwyutagihjxsctpaigafipawownlpnxdcrrlvuqrkqxmxyycvlsfmkhmqkpfnsmdqruxckkolomadluoajvmvioamsisnvnpmrfelzfjklcehulcyzgcpimcywugluewxifcsuyodqswidfkzgdizirlbxgyucmmhbuawkmoiuzorhuopldvogbgpeglukrucostriczywnvhezoauklbyhhudwjenubjqsyzccqvvqmndnklizxnyppxdtobmwyclshbqirqvbkocjnvrvyiqfnypzbaecucuvnsduzshfdveweeqacmjcuhpfblybuztkspnlosawabqihhheskxnckwoaamchmkzgrlgbxpdcwuzaifvklilbnotsavrvgwirazbuadkhhyovwrgscoqlayaavxftmveleskalooesrtlvdowwawusydcavcfggzclmvhvbdwoninjfhthwmjkauvwiaykxeritiitsaibvflfrivfcmsftxnfqxcbrpsicwvcfdhdhkgwvbtrukjsxzvdyayevwdgpnqfmexcqbhslzrwwbjgxpmzicnzvfrgmhbhfjsqskhfhouhjhavzlyektuzasrdydsmfvgdottmahinuvaypqipnofcrtwmxnkziosxcxiuvcmuwobzjqasxwygtsiojbijvzwxihaitnrsbctfffximerozoejnnnoxkjcrtsdbasxgzjpzrncynjztxsxoquozagivpdxkwvobwmqhxnzkkrfxjvwmoyazjprdsnbpkwzurkoqsblxsxntrkihdktdxkebkapqdkzcpuchvgejrbevqmdgzvdrcfhchvzpijarlxlbxpprtcchhypfrcqeyyuvdlveawzcevxpxbomkzrrbwcssccsfpolppjecpedfisscoaclxeuyoagohmreljohmsxxugywuqwnldjyvqvnrhuuqsycvsmuufwztzyqnjwtjpcoadwunbpqydzhrhdlqazitnvmwkwurhuixgevcoovddptqqlbdyegweeugjktrwdeavujszcogdksyiywhixtfkdmuywuzebxfbtqxnrfzebwizjofodzobvfqzqzhwvmkesggtwnahgdoitkfjswqbymoxbqitsdcgiovcxxhoghtcgyjmkcrqmzygarrbranyozpmzhkqaodlidppdxvumosgmcyywlucfxnpebcsculhteecxhozjhjefrrufdedixquaoyjjudqukuhlnmlwibrlbgzbmqeobibojcevipzalwihhqmciqajfqjrtlgqxhiilrirdngzgvevmwgporidlaojpecjlwhspenxmhhjikkzpfxywsyuksdxwngplfjcnoofowzfuedkjizlnwckezcpjwbssnfyaaqzhfnfxommvkwxwyadwjobkjcijllqhbktiqjneqgqtfeqaxctkhlyejrdroohqquugmthcknayavvzpfzkgrtdydflumpmmshmyzhlbnikysdzmfcsueyaxscuwlmpbkbdhflxyjatibnqglhoyqvptsweadyqdushmjctarlrfsnuwwicxcosyjijgafvienbrmjjkkppyemztgvqgmhaoodiutqyhlzltwfdcetfbhwqymyephwzlgiqshrzrrmxnkubcklrzqwhtmujvxlzwqlyqvrqsrfvgrwteawknkaxfczyhtaoveixmwasqkadzxczgfkusdslzrmxwctcjvtapckdjhgzjxmqwpoorfrnlspsnsbdomnbuxhcydilpnpnoasrzksqrvqnfppknxdgcjvmnneocbqbfvyqyrhicrwgmsqxicihghboebypwlognhgeoxzsqqtrqlelizaichkjqzgffeayhgwwvuunlnccojbllihtwfbimfcviihskwkfihirydmxumqstqsyjlsxqsmgowxtjroucvvsmywxrmhcuugnwkykdmiiffgniwopfybckqqgsouxnweafomyeyceomqbsoiemkwrchbsmznbuslaxetvjlakeljmjvvparxldyymdbbifhxeanazazkdkwqyylutcsnjlrkvhulhcyifceatdfnbtnyecpgejlmnbpzhpgvhrjezjlykskioqnrkgshkgjlkgbtlcmjxzbiydzjvpogfcihtxvimvcbnvihyquwktxpbqjitdredfnsczyoowanvwlfwlvnbtlqjzzxyfvjrcgocvkcinqpvvgyneqcskmngagoejafszejmwtsglqqnctmhvlzplclwjqfpotqdijkhwxjpqugpdqsmtseaagevbhbtccrqudqhcltinsteaccpkptyacpjowbgvcqrjdcoonmkoqamldzeuqrlptypgbiahuwkftgygenhriiifxkwqwrzljygjffonztiagyfpvtnaqjmblnyvkraxneyjhbmrjpamxclgzroibelnggazqpglxqbjfcsmyndpabxjvyyqtmrqlhcpjxtpngtfqbrdremegasdgfldgzpsilcsogojlwgjrdkgutqieteytpgycsnicviyatqjgrbgoeazihqtmqqptxnzfsybmcnngxafsfosotdrqdfvmndfzoitevapdhhztsjirzeibpkcbukfwlizeafhuclgszqcxnhfrhkwwbdmklzrgkosnukqvxliuiyfmjaysoxvokylvkauasueearoqryjgvjavgjukzfhccjfqiwjnxfgmrtpcjjouzdkqgravgmjiowiyxbxxuzbsxjapzobbszsiustzfakauvixpzxtngkypjjgitxcyktlprxrlfkgsngdoxcrbqgzlcnxsdriowsiaohxiaqyddobiodxdgitertvnplrffxftjcjzqfkilyhfylbqppgoohmisdqxxjkvinrauvbjzqdaijkqyzjpfftziprchuqgokcsekaqfldghomzuqummigofjjcwdnsqgcffhhhlszdrlupnkphihcfvosberffeyntqvbqhwihxfzohuxepvuudalucbpkrbtehxtrrwncxalwnegxlaxoajgfcbvxsfcdtzpcnxujgheslrcxthxwyihnvmobibgwnykruhxocdmgwleybzyrxkipvnyeofltexfwszaxchpyzipvdveaildatyuoytmvtbepzqaigkebntkxabtifvltkiaqdljejdotlwkunpuwbitipktihwhufpvwgakyxbhkhxormigyxlvpxuvzmdilrrwasrafzwxekdhsweongtxbrsdlbvxocfysxwjctrcjkfyclybjzofbhgrtxkwtuxbcstxjfqbvkumbxwytxctdgvgoxeyajudrtqbbeldcppvgkepdwesvddtehvukrkeswziywnhgofcjxtpsvdtvrlxsxgiophujlgetzthjoglajjlnwhxuouirxcghdpmwzukeogwvhszsqmnjomvnfvvqkxybqmvnkwckdxtssllvzgvqgjkjsazjwdiccxsncuxhocfltiknglwmceyipquoebbivrmrhlbuqpcelxhviagmazifbydodvyhmdaaynkbpsugevfybepmhshwfctdawrwkmceexzhzzshophpauukhyygsxojsjkcmaqeqchbnrfvothsegprrokcfxqvjvlrwwusakqighgizdaxbeafkrezszzfpeyaamoidssrwlsetgqovfxlvutiputxupqdfbcpzkfnissnklohdhsocqcmhjiuespzqfpxxgtyopuxnztwwyxurpammqfixvczfytgmudhkkqglidlnrdiziyqlorcpfclavrkcnmnuudbzmpjuwnbshfifzjntzwctnhvimmhuxvikentngmrjdfniakasltjzmoarluqzwkmxyvuriagixlwjzfauqymrrpvgqmznnjyjwibqdnovqdgfmbstoxgqwifobwlkrhkntbkjpyybpbvrqyfofvqyalgxkcaudabhqyicsytqkmlhdkkseayxfoseawevapmxpinigtzaikvafrbdaycsagthctngpchywybeuvckfirkmanqfrdwbqqlttsdtulurutmygacpswurwbzrawiyoryiusaguewawpowxufjjjhyenfzunuzvmygsyyyxyclleybvvakjouezcezmwmfxvlbsubnekumpnkvwfvylgeihcppuiymstcvbhuqwiuarcrkpjljafihwcghbvtjygwekzwdcxrocezhiyjkdktmwnaunwkkrevxpwgoqsmakkicvqkalbwgwgpynungzllydlqsifltdnaplwfgobnwabtiygbwrgypcbtmwzyifqnswokdnzvzpaupjleyquanmbbdddlskpjfsbyzqtmsshebfnakctckcuejbntdalrtnszlgecxpjtoisfeoxowwnqcxxitgribkpzbagcrnggrwhkenzumkckfizaoqunjwvrewkswddunhwgbhgmnnjtrgbejvupddoygtnfogmbdwwratpeqqwrierpvddjhctzlybetkmgmwaccoqtifdicxymirqitvdpunuyjdfweypuvlyibpzmghbkcrvuvsegqvbenmpznmbqrddhzegfvosjpjyixucfhhwolvjartvsafoxnusizacbirqfvmtoroxxfvrfugjsvhslkxbkzthorfotwstoysugrlceyceyiajptmvcvfyudrvmgleqexabxboczsielyuiknrwskhaoqgjdtrmsdalohkcrxajvjnndlmpadxpjrvcnqjpaeoapbiwyamsohlwhrmrxrzxnreulbdrovwclkzpnuaxpzukfwjzodyvdurtagjwaroidonnqwosvdeeuprmvngqecevkdwnljhhmkvsxktnwncmwdyosevoqsqobpodoptqjuimyjvwizjitzzrahkvyvlcuelahglchofxrprnfuqjuwcfowvxjyfrkzzpoobukgewaugsbrjxzugmfwphcvdxdfrvsddntsxqvpgehptipblbohruqmgigylzciltakimfoudvuovhdayaxcrtovamhmgipsbhtwserrwafkcdhisgavkevygoiqgunwnpikthzrvzebtvjjbfbulahahenxioiaujhqoewlancyttmvwfbamksaksqkkzfvbqptdzduylztnfuwhcimsapxbnmkdyednsjhcmcpfwlneuxkfcgvgsvypriclvkibyacvsnguitkvuhjljbvmuooaomiwyuwrizocmcbelltpvkzcwihnelmkipdpsiedjwnhcghxrqzyzovhkmoubfwykznbrbbwcmlnxjypuwyqnvathlqdklddjwwkzshttifgunnmontphimsjgykeyudkqxgwfvijrcxmmdoixjohtypbbnkebukxvpghlmmngbjbqjjkuqhsrbqwkvayopluthlmbilwqazyuwygrxgxnbkwatgmtknnomnzzumdwhqtoksqybnnxhodwkjrfshidvlglcspcpytwhrtodqigonrssgohfprztkzfyqerffdejezuomzhkpriepvnadmrvyfkkuphiutcrflxrpcgjuznaplfirbvdphywaadowspimhsgwkkupsnjmhbgbeqwwikqcemmtovsptyqrvpnyauswlcxvzuujpwjfvgcxknwbayrxbviiharegbjcvinqjflnegdhmuqglfqwyoayzhojxpqzbbrwjxfuzswaaxpsrbqtsuyexzvszrxilcnwrknuvqgghrsorullriwmirokdmoizorhkzhrwbgrzsnaobyigvasiwnpmualviqfkmhobvhuqyxmzelzpkfxqqhblmxstvopbxiuosuzlkhmykcztvspxsaduswmnebccgwxygmenrkemfteokpectbnhjtjslbbspoqpvhonwjhwalkqjpvpfohszewdbwjjzatavyzhoasjsodtmprheokjycycdyjygnoqegeohyjygcgyojxvxmylgkdtpnzqwhmcxmelekzcothlcijgknljmtkphbnaekcubpnsjltyycsiqjbeazjwqnvzsxhsohngqmhqprgpfgqeooqxuugipxjpqrawyqhzhvtwxmdfawzhyygehrjbvufcgwteavwgxlqqppqtunzairlynnuyepwevpqsodlevgmmdllbeqeioumxadrisrrmmzdavksrbmukygqdxrmnrqmnsknfcesizreyoeknywxwjqdtrrtxrahhoevdiubdsoagkjunnhauzjpxrhrsuiwcxshziuhkzelrhcsmvuprmyekdflmcgmpiiqkxiklcqkspaidbzdzwrnwnjjmhztnhjntvbnafbzceqkegqgotgpgrdqtmsxtyftweckrnldzbjmszjwfdssxtucwhyuffcfscnvhuxliieqrdudmeoyojrmaczxksavqxkyirwstvknmyxsbburoudanijcaunfvytqdxpuhvcxbogqbjvglyjnfikcopoegdgmkslpandhbqxvlcfavzihkgmtpklofuvwysszblrplaysgkqagatlwqelomztmsadjzktblzsmkhhxitgwdhbjpsezptjbajqimtuxbsoqzvymzanvuybilzgaktfodsbkpfxwmuoinoiqxijzodpgjydfmdwylpkvbntjauzeowomymshhwnospaynzqavvnzxcdrqzssegpcyuppqpxkxdeeelupbblsrmgtpulupkgpfnulccedpiwxrxenryvnzcbepvtvxwinepkiltopezukvyjzmivkkacvibsfkkeopepmwrvpremnetgrgwpxomrzxxwsiflsnnexddshfgvgiggxprryhbhwejejiwwisprewvtvysovpufgubnyuzpfrwqgacsiashethjpavdqjzvgcnjilcsrfnukwkhgwjnqhcfnyrxgczpedchtyvbncagzqqknguxwxxlbiorqfqwcfsoidbyxxybxqkulnrvqulublmkvvkmufsothocirhsxryvjraoqvurhefllbnqopmwktdwlzmlqzizmtdbwjrnbnrpwykxvkhoblazglytggdyajcipywwnomqzflocyhobgnlhvxyvwrcrbihhbiwvymwxhbewqqnkkjwoffjhndwnmoobpwpuiqdjjrmexkcvrymadnkngobazjadjrxktkaqsytcipekbgjbmelhxveltlarkcbijfonsynggmpvotecatgolhfoniaqenouixnzcxgcqoyjchwxmqkitjpkhzdjwjafaqopljwuaydispythponkyasfzrglffmchrvjzofwvhvqcakisnxjdgobxxxujqkvwgtqhssdofazusuuickewkgapyakqoivnunnseyddzvhlgynenheuxqstopunwpaonknkshrupdkbppyryqftdoqzxndrgvnwtufkvkstzvpetcfvvxlqgwaejqdgkdfswgmadpewjqfynjprlzwlvnojzxutzhlrdefjohgxzeygsfxwrnebnzlzpyqytofohfoitjfaegvkbezopgercgfeohrdiyttqgmigigbbmewymcldfiyonknshmsilmbgjscgsrnbmvqsmhdiweqrbohdpufvgargxvlmsbzbpqqsnjamztlimyygofobqveapgudkatmuddrnnmpdyupuumbllbexdbkqzwuvownvpwdypjgvfujrwueftjmxnlaomuesebdrzxvwoehkwkvenljtgmincckezgxrkupekhvhodqcsrncpxamyixzxuxdvuuskqartgiraegqhlantpfsxdcflrdqxknrqhvroziuuhhzachmrgtqvrnjajflbrxjpnpqeuanpyxnovvmhrbbzurkelnzeegxsktwkhcolpknsukmrjdebhdtfspxmwbhemfoevrerhwghgpyoesfyhsauspeqdljxmzhibfeffwakalxxqgursezyyctgookqlgqlpdlmmwkqdzaatptcjogqkbwhuqhwepiyqegpvkzkjmsbvvtgvsilbdguqcdwlyutcwpmmyljtmtlatoalmwapitmlvdpwvojyvkmheubsmeqdazxxwkfacllflayezeapuhpdiszsmcwvhadrvgiufjcrwrcmrcpeftumvndhnqfsujpfpxzakvlaovkxpruigvndthiusonpfrvpubaguxqgolgfqqxbbicjxwxodivwoatjyrehyuwpvquludhwdgtsihojaixrbcgtmnlmouiprwhgrwrhcqzqwzmxnsvkwzvgzqsbveglajfkcehpkamllwgsbhcvolrsxlzmmvryszgptodganqqjbrhplnqdlepklkrmnovjxirxfauzsizhjlchlvvzcjmrfmlsfgnrbtbwmowmwvpmqomurtyqjcdkgmeizhlabehtaxvzzikpbkdemihvhqwyzlcejfbedptvlqoomzqjinndygrufqmyrdfeghzjqlxsspbaxqsswlicelxqvrriovmtrnnfykqdnyuxyhbssmvhiazlylkgapqjlslhepfxqblpjkbhyekrxkrqakdlbdecmobxetayuesopuvquaybusiuctqkldwgyjpsqkgwbftwuplgxenqyokldxoenydqpiepyxzmtsnoomdbeccasxesiimrrvocxkzezufxkgxknhfwmloevxjuvytsjhgincpkfnvgenckkcmtzobuakrybgzpaypyfphwpvzaqfcoulygmvvyunthrhxjhdekohiqxudnixoplitignakjfoxjpkqocxknlyqilxcaxbgivqemkypdaedgizekjijqleadxpmuqxcnlhuytygxehugvxifpotuxzmdqfsizcjavgviywkuizdtmstexlstmjfndsxahsukpnrjgcrzcpzlozctnfoxwzffjhmpxhidwogvhpqsteisyqpkssgivtfecmnisufenyfyzachcecvpklzxqxfahecuqjyiiwyuxanzlcjesyqpyluxnsovkjkbetkpifpqytbgjaqjbmdnulcneayejwkgzgeuiaqbzsqosreqdanofptccolksecvqgtddmaqcvaaldbkmuqrjsmrgetfzxvgujtzjisbzwtsqyzxmmfthluljxbbazextfutokwwekzhdtpiqfjzcostatqatbwbtagparunafqsqxbocfbmwdxngvaaallbrjncnutkmxnciupnyjeuvicjtobeakjddaavvyxtrqxgjducknhqqstwkqyvqjvfnsodtpyusdnrgbskzjgpqroslvsdotzqyhyxdrwxewiswriqsobszydrujsqrirsbrbvxplleikkynruebygevygqbcurmdrinviurluxhjyrqlltrxccxsqbofhtbzvmajoznozotnoxvvzsrjoxoxrdbiryzycjbjoncaktoivdfwwqmsopqqtzlzfbbuesafkdchsfouwvmfartzshhmfqrcpqowzelzbnsttgjecpmtpqzxjpziponxupgyzolgaumpbvcfwnlbiexmxfvfnyzlwrrkcctyhnjqmfshpflvumkgdlpygsundmonvymxnncpnmqznzmxfntnfoxxgwpsailulkflejxbdgqjmxsmxuctogvtcpbjyuwbugoftnkbyqfkjzegivsxtflzbnftxqqxvqfrmxpppbaoupyjyhlcfwlogfcgjfghizmoejpvdimialgtrwjaegjoyjhjgvayejcxvyrbrvszuocwhwogxfomxawjqfjodwjzimlnqnkuvismtsumucjrlhttxfnbcrvvgnuhpzymihomppbalfabfwhzehhmbyxbeeoowctxwytosxpcvbqgvyprjnrbpbbpmywiuirvhgoptpupfmqpagxqubukmjhltampfwaxathhxxeelpwhvyitrggqckmhifjolfzfucnojobblkfhorwqsptififxjgjcfyxwvxcqkmogdzmbgbsfygfpixljmukxgworeludrcjzbkjfidpizzegwsqgebjmwixctjbbjbdjogtjnuihndxyreqlpvliehnmstlskaukeofkcupkjdnsmegditqxmigrhiiuuzbuntbacojozhglpbvvkzjvwroigkmzshbkwfpcixnfjzzorjfqtkksrcgtoxumasyhbiymgwxaawvijeueajbjptbczdzyozytmcntuzhhwzcnuyyknuhqffnlhwjzwkuiuwplghzkkqqrtfcqdunjjbmzqdlsxdcsiqxpfywfssxrefkvbjhphtfbhhysuraxubehbovovhnrtaurekkzigggbcycsvyxklxopndcrzeavnmciruxfjndamdhqyftzaefrvixuwyrygpudqbhfprnyhwkvfjrcqrtbdbtfqutgsjjxkzikfnlaosdqajyvvapduyckjfdkfhtsmlttizekgyatrmmzobfxcvfxggsglbmhyrkgmxwdzfpytgkvilhvthnzclbgwdbmjrmofonopaszujtqeobqkbmqhxrsvgqruknrtmccjlcietxnczyzydyomfiutufgfieeabfanehcoputjsqbhtticslhqrcxogmutzkqwrwaehpdghtmbvcwbhpnbgtxezwfwwlpmufnqlfymnflgmgroqlfknseonqfjhvkjfdbtjbiosqvrgwtwvvmvauqzuvbhvkbjqunmphwqbmmvogglerdfzdqvdbumpepxuvcsiivnvpakmaznvmqwronselkjtnhiigavezrbwskxzkqaihycmmyblkyilkxsuxdwuqeypbaoxuwaasnabjvzejjjaemlepwujtcuunxwfljkfjbkbsqrivgsczhsinyzdjsygcigiqnbmjqrmvrwlwthtypwihabnjepmzgldashenr"));
//        System.out.println(reformatDate.maxNumOfSubstrings("aababaaclmcdef"));
    }

    /**
     * 第198场
     */
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        return new int[]{};
    }

    private class Locate {
        int begin;
        int end;
        public Locate(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }
    }
    public List<String> maxNumOfSubstrings(String s) {
        Map<Character, Locate> map = new HashMap();
        List<Character> chars = new ArrayList<>();
        for (int i = 0; i < s.toCharArray().length; i++) {
            Character c = s.toCharArray()[i];
            if (!map.containsKey(c)) {
                chars.add(c);
                map.put(c, new Locate(s.indexOf(c), s.lastIndexOf(c)));
                map.forEach((k, v) -> {
                    if (s.indexOf(c) < v.end && s.lastIndexOf(c) > v.end)
                        v.end = s.lastIndexOf(c);
                });
            }
            else {
                int tmp = i;
                map.forEach((k, v) -> {
                    if (tmp < v.end && tmp > v.begin) {
                        if (map.get(c).begin < v.begin) v.begin = map.get(c).begin;
                        if (map.get(c).end > v.end) v.end = map.get(c).end;
                    }
                });
            }
        }
        List<Character> result = new ArrayList<>();
        for (Character c : chars) {
            if (result.isEmpty()) result.add(c);
            else {
                Character pre = result.get(result.size() - 1);
                if (map.get(c).begin > map.get(pre).end)
                    result.add(c);
                if (map.get(c).end < map.get(pre).end) {
                    result.set(result.size() - 1, c);
                }
            }
        }

        List<String> stringList = new ArrayList<>();
        for (Character c1 : result) {
            stringList.add(s.substring(map.get(c1).begin, map.get(c1).end + 1));
        }
        return stringList;
    }
}
