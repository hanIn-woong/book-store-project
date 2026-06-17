package com.bookstore.domain.detail.service;

import com.bookstore.common.model.Book;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AiBookService {

    public record AiSummary(
            String oneLinePitch,
            String detailedSummary,
            String targetReader,
            String purchasePoints
    ) {}

    // 판매량 상위 6권 — 직접 작성한 요약
    private static final Map<String, AiSummary> SUMMARIES = Map.of(
            "P1242", new AiSummary(
                    "마법사가 된다는 것 — 세계 최다 판매 판타지 시리즈의 완벽한 첫 장",
                    "부모님을 잃고 이모 집에서 구박받으며 살던 해리 포터가 호그와트 마법 학교에 입학하면서 자신의 정체성을 찾아가는 이야기입니다. " +
                    "우정·용기·선택이라는 주제를 마법이라는 환상적인 세계 안에 완벽하게 녹여낸 작품으로, " +
                    "어린이부터 어른까지 세대를 초월한 사랑을 받고 있습니다.",
                    "아직 해리 포터를 읽지 않은 모든 분, 어린 시절 읽었던 감동을 다시 느끼고 싶은 분",
                    "시리즈 7권 전체를 완독하는 것을 전제로 시작하시길 권합니다. 1권은 그 긴 여정의 설레는 첫걸음입니다."
            ),
            "P1246", new AiSummary(
                    "어른이 되어도 잊지 말아야 할 것들 — 시대를 초월한 가장 아름다운 동화",
                    "사막에 불시착한 비행사가 만난 어린 왕자를 통해 사랑·우정·상실·본질의 의미를 이야기합니다. " +
                    "'중요한 것은 눈에 보이지 않아' 라는 문장 하나가 수백 페이지의 설명보다 더 많은 것을 담고 있습니다. " +
                    "어린이를 위한 책이지만, 오히려 어른이 읽을 때 더 울컥하는 작품입니다.",
                    "바쁜 일상 속에서 잠시 멈추고 삶의 본질을 돌아보고 싶은 모든 분",
                    "선물용으로도 최고의 선택입니다. 삽화가 아름다워 소장 가치가 높고, 한 번 읽을 때마다 다른 의미로 다가옵니다."
            ),
            "P1241", new AiSummary(
                    "자신의 전설을 살아라 — 꿈을 향한 여정을 담은 전 세계 7000만 독자의 선택",
                    "양치기 소년 산티아고가 자신의 보물을 찾아 이집트로 떠나는 여정을 통해 삶의 의미와 자아실현을 이야기합니다. " +
                    "짧고 우화적인 문체로 쓰여 있지만, 페이지마다 삶에 대한 깊은 통찰이 담겨 있습니다. " +
                    "전 세계 70개국 이상에서 번역 출간된 현대의 고전입니다.",
                    "지금 자신이 가야 할 길을 찾고 있는 분, 꿈을 포기하지 말아야 할 이유를 찾는 분",
                    "두세 시간이면 완독 가능한 분량이라 부담 없이 시작할 수 있습니다. 인생의 전환점에 있을 때 읽으면 특히 강하게 와닿습니다."
            ),
            "P1243", new AiSummary(
                    "레오나르도 다빈치가 남긴 암호 — 멈출 수 없는 종교 미스터리 스릴러",
                    "루브르 박물관 살인 사건을 실마리로, 하버드 교수 로버트 랭던이 역사 속에 숨겨진 거대한 음모를 추적합니다. " +
                    "기독교 역사, 미술, 암호학을 절묘하게 엮은 전개가 책을 손에서 놓을 수 없게 만듭니다. " +
                    "전 세계 8000만 부 이상 판매된 21세기 최고의 스릴러 중 하나입니다.",
                    "빠른 전개와 반전을 즐기는 독자, 역사·종교·미술에 관심 있으면서 흥미롭게 접근하고 싶은 분",
                    "실제 파리와 런던에서 랭던 교수가 방문한 장소들을 구글 지도로 함께 찾아보면 훨씬 생생한 독서 경험을 할 수 있습니다."
            ),
            "P1245", new AiSummary(
                    "모든 동물은 평등하다, 하지만 어떤 동물은 더 평등하다 — 권력의 본질을 꿰뚫은 우화",
                    "농장의 동물들이 인간을 몰아내고 스스로 농장을 운영하지만, 결국 돼지들이 권력을 독점하며 타락해가는 이야기입니다. " +
                    "러시아 혁명과 스탈린 체제를 동물 우화로 풍자했지만, 어느 시대 어느 나라에도 적용되는 보편적인 이야기입니다. " +
                    "100페이지 남짓한 얇은 책에 정치와 권력의 본질이 압축되어 있습니다.",
                    "정치·사회 구조에 관심 있는 분, 짧지만 강렬한 메시지를 담은 책을 원하는 분",
                    "두 시간 안에 완독할 수 있는 분량이라 부담이 없습니다. 읽고 나서 주변의 조직이나 단체를 다시 바라보게 됩니다."
            ),
            "P1236", new AiSummary(
                    "빅브라더가 지켜보고 있다 — 감시와 통제 사회를 예언한 디스토피아의 정전",
                    "전체주의 국가 오세아니아에서 진실을 기록하는 일을 하는 윈스턴 스미스의 저항을 담은 소설입니다. " +
                    "'빅브라더', '이중사고', '뉴스피크' 같은 개념들이 오늘날에도 그대로 쓰일 만큼 현실을 꿰뚫는 통찰이 담겨 있습니다. " +
                    "출간된 지 70년이 넘었지만 SNS와 AI 감시 시대를 사는 지금 더 소름 돋게 읽힙니다.",
                    "정치·사회 구조에 비판적 시각을 갖고 싶은 분, 자유와 프라이버시의 가치를 다시 생각해보고 싶은 분",
                    "읽는 내내 불편하고 무거운 소설이지만, 그래서 더 오래 남습니다. 완독 후 SNS를 보면 세상이 다르게 보일 거예요."
            )
    );

    public AiSummary getSummary(Book book) {
        AiSummary summary = SUMMARIES.get(book.getBookId());
        if (summary != null) {
            return summary;
        }
        return new AiSummary(
                buildOneLinePitch(book),
                buildDetailedSummary(book),
                buildTargetReader(book),
                buildPurchasePoints(book)
        );
    }

    private String buildOneLinePitch(Book book) {
        return "\"" + book.getName() + "\" — " + book.getAuthor() + "이(가) 전하는 "
                + book.getCategory() + " 장르의 추천 도서";
    }

    private String buildDetailedSummary(Book book) {
        return book.getDescription() + " " + book.getPublisher() + " 출간의 이 작품은 "
                + book.getCategory() + " 장르를 대표하는 작품 중 하나입니다.";
    }

    private String buildTargetReader(Book book) {
        return switch (book.getCategory()) {
            case "판타지"              -> "방대한 세계관과 모험을 즐기는 독자, 상상력이 풍부한 분";
            case "소설", "고전"        -> "문학적 감수성이 높고 인간의 본질을 탐구하고 싶은 독자";
            case "디스토피아", "SF"    -> "사회와 미래에 대해 깊이 생각하는 독자, 비판적 사고를 즐기는 분";
            case "로맨스", "역사 소설" -> "감성적인 이야기와 풍부한 시대 배경을 좋아하는 독자";
            case "스릴러", "모험"      -> "긴장감 넘치는 전개와 반전을 즐기는 독자";
            case "비소설"              -> "지식과 통찰을 추구하며 자기계발에 관심 있는 독자";
            case "어린이"              -> "감수성이 풍부한 어린이와 함께 읽고 싶은 부모님";
            default -> book.getCategory() + " 장르를 즐기는 독자, "
                        + book.getAuthor() + "의 작품 세계에 관심 있는 분";
        };
    }

    private String buildPurchasePoints(Book book) {
        String stockStatus = book.getUnitsInStock() > 0
                ? "현재 재고가 있어 바로 구매 가능합니다."
                : "현재 품절 상태입니다.";
        return "누적 판매량 " + book.getSalesCount() + "권의 검증된 작품입니다. "
                + stockStatus + " 정가 " + book.getUnitPrice() + "원으로 합리적인 선택입니다.";
    }
}
