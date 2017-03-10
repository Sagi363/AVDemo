package com.sagihatzabi.breakingline;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sagihatzabi.breakingline.items.SagiVectorIcon;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment implements FoodAdapter.OnItemClickListener {


    private static final int NUM_OF_ITEMS = 3;
    private OnFragmentInteractionListener mListener;

    private LinearLayout mCartLinearLayout;
    private RecyclerView mRecyclerView;
    private ArrayList<SagiVectorIcon> mCart;
    private TextView mPriceTextView;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        mCartLinearLayout = (LinearLayout) view.findViewById(R.id.fragment_cart_items);
        View btnPay = view.findViewById(R.id.fragment_cart_pay_layout);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPay(mCart);
            }
        });

        mPriceTextView = (TextView) view.findViewById(R.id.fragment_cart_price_button);


        mCart = new ArrayList<>();
        mRecyclerView = createSnappingRecyclerView();
        mCartLinearLayout.addView(mRecyclerView);



        // Inflate the layout for this fragment
        return view;

    }

    public void onButtonPay(ArrayList<SagiVectorIcon> cart) {
        if (mListener != null) {
            mListener.onFragmentInteraction(this.mCart);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(SagiVectorIcon vectorIcon) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(ArrayList<SagiVectorIcon> cart);
    }

    private RecyclerView createSnappingRecyclerView() {

        int width = mCartLinearLayout.getWidth();

        // specify an adapter (see also next example)
        FoodAdapter mAdapter = new FoodAdapter(mCart, this);

        // Create FoodRecyclerView
        RecyclerView recyclerView = new RecyclerView(getActivity());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        return recyclerView;
    }

    public void addItemToCart(SagiVectorIcon item) {
        this.mCart.add(item);
        mRecyclerView.getAdapter().notifyDataSetChanged();

        computeCartPrice();
    }

    private void computeCartPrice() {
        float price = 0;

        for (SagiVectorIcon sagiVectorIcon : this.mCart) {
            // TODO: Compute price here
//            price += sagiVectorIcon.mPrice;
        }
    }
}
