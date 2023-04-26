import React from "react";
import * as style from "./style/BottomNavBar";
import Link from "next/link";
//BottomNavBar
type NavElement = {
  name: string;
  link: string;
  imgSrc: string;
};

const elementList: NavElement[] = [
  {
    name: "Home",
    link: "main",
    imgSrc: "/navItems/home.png",
  },
  {
    name: "Costume",
    link: "costume",
    imgSrc: "/navItems/backpack.png",
  },
  {
    name: "Quest",
    link: "quest",
    imgSrc: "/navItems/quest.png",
  },
  {
    name: "MyPage",
    link: "mypage",
    imgSrc: "/navItems/cobbyface.png",
  },
];

// const RedirectButton = React.forwardRef(({ onClick, href }, ref) => {
//   return (
//     <a href={href} onClick={onClick} ref={ref}>
//       Click Me
//     </a>
//   );
// });

// // displayName => 안주면 에러 뜸
// RedirectButton.displayName = "RedirectButton";

const BottomNavBar = () => {
  const userId = "test"; // 나중에 대체해야함 useRouter()

  return (
    <style.NavWrapper>
      {elementList.map((element: NavElement, index: number) => (
        <style.CustomLink
          href={`/${element.link}/${userId}`}
          key={index}
          passHref
          legacyBehavior
        >
          {/* Link컴포넌트 수정 => 해당 버튼 부위를 누르면 이동되게 */}
          <style.NavItemWrapper>
            <style.NavItemIcon imgSrc={element.imgSrc} />
            <style.TextBox size={20}>{element.name}</style.TextBox>
          </style.NavItemWrapper>
        </style.CustomLink>
      ))}
    </style.NavWrapper>
  );
};

export default BottomNavBar;
