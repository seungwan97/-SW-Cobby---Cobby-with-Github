import TextBox from "@/components/common/TextBox/TextBox";
import * as style from "./style/GithubBadge";

const GithubBadge = () => {
  return (
    <style.GithubBadgeWrapper>
      <style.BadgeTxt>
        <TextBox
          size={20}
          content={"# GITHUB README BADGE"}
        />
      </style.BadgeTxt>
      <style.CodeCopyBox>
        <style.ReadmeCode>
          <TextBox
            size={15}
            content={
              "https://cobby-play.com/baefrica/badge..."
            }
          />
        </style.ReadmeCode>
        <style.CopyBtn>
          <style.CopyBtnImg src="/copybutton.png"></style.CopyBtnImg>
        </style.CopyBtn>
      </style.CodeCopyBox>
    </style.GithubBadgeWrapper>
  );
};
export default GithubBadge;
