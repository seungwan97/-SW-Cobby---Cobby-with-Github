import TextBox from "../box/TextBox";
import * as style from "./style/BottomNavBar";
//BottomNavBar

type NavElement = {
  name: string;
  imgSrc: string;
};

const elementList: NavElement[] = [
  {
    name: "Home",
    imgSrc: "/navItems/home.png",
  },
  {
    name: "Costume",
    imgSrc: "/navItems/backpack.png",
  },
  {
    name: "Quest",
    imgSrc: "/navItems/quest.png",
  },
  {
    name: "MyPage",
    imgSrc: "/navItems/cobbyface.png",
  },
];

const BottomNavBar = () => {
  return (
    <style.NavWrapper>
      {elementList.map((element: NavElement, index: number) => (
        <style.NavItemWrapper key={index}>
          <style.NavItemIcon imgSrc={element.imgSrc} />
          <style.TextBox size={20}>{element.name}</style.TextBox>
        </style.NavItemWrapper>
      ))}
    </style.NavWrapper>
  );
};

export default BottomNavBar;
